# TestFragmentState
测试androidx中新的Fragment的使用，利用setMaxLifecycle控制不显示的Fragment的最大生命周期，避免不必要的代码执行，提升性能

## 问题
在以前使用Fragment时，如果使用show/hide切换Fragment显示，由于Fragment都attach了，当Activity生命周期走到onResume的时候，也会触发所有Fragment执行onResume。
举个例子，首页四个TAB并对应四个Fragment，当前在第二个TAB，应该渲染，展示，发出数据获取请求等，但是其他TAB并没有显示，不应该做类似的操作。
然而如上所述，如果此时从首页进入二级详情页面，再返回，首页onResume会触发首页四个TAB对应的Fragment执行onResume。（而此时返回首页显示的是第二个TAB，理应只执行第二个TAB的onResume）

延伸使用场景，从第二个TAB进入二级页面，二级页面点击事件是跳转首页第三个TAB，那么此时如何精准去记录页面的PV?（通常都是在onResume/onHiddenChanged的时候记录，然而返回的时候所有TAB都执行了onResume，切换又会执行onHiddenChanged）

## 新版本Fragment的解决方案
FragmentTransaction新增setMaxLifecycle方法可以控制Fragment最大生命周期，可以实现让特定的Fragment可以执行onResume,其他Fragment只能执行到onStart。参见ViewPager2的Adapter

```
        void updateFragmentMaxLifecycle(boolean dataSetChanged) {
            if (shouldDelayFragmentTransactions()) {
                return; /** recovery step via {@link #mLifecycleObserver} */
            }

            if (mViewPager.getScrollState() != ViewPager2.SCROLL_STATE_IDLE) {
                return; // do not update while not idle to avoid jitter
            }

            if (mFragments.isEmpty() || getItemCount() == 0) {
                return; // nothing to do
            }

            final int currentItem = mViewPager.getCurrentItem();
            if (currentItem >= getItemCount()) {
                /** current item is yet to be updated; it is guaranteed to change, so we will be
                 * notified via {@link ViewPager2.OnPageChangeCallback#onPageSelected(int)}  */
                return;
            }

            long currentItemId = getItemId(currentItem);
            if (currentItemId == mPrimaryItemId && !dataSetChanged) {
                return; // nothing to do
            }

            Fragment currentItemFragment = mFragments.get(currentItemId);
            if (currentItemFragment == null || !currentItemFragment.isAdded()) {
                return;
            }

            mPrimaryItemId = currentItemId;
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            for (int ix = 0; ix < mFragments.size(); ix++) {
                long itemId = mFragments.keyAt(ix);
                Fragment fragment = mFragments.valueAt(ix);

                if (!fragment.isAdded()) {
                    continue;
                }

                transaction.setMaxLifecycle(fragment, itemId == mPrimaryItemId ? RESUMED : STARTED);
                fragment.setMenuVisibility(itemId == mPrimaryItemId);
            }

            if (!transaction.isEmpty()) {
                transaction.commitNow();
            }
        }
```

## 玩法
setMaxLifecycle仅仅处理生命周期，不处理显示，所以还要配合show/hide控制Fragment的显示，如果原先有处理onHiddenChanged和onResume，需要考虑如何修改代码
```
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.hide(fragment)
            beginTransaction.setMaxLifecycle(fragment, androidx.lifecycle.Lifecycle.State.STARTED)
            beginTransaction.setMaxLifecycle(secondFragment, androidx.lifecycle.Lifecycle.State.RESUMED)
            beginTransaction.show(secondFragment)
            beginTransaction.commit()
```

## Log
```
2019-08-16 15:19:25.073 30945-30945/cn.rexih.android.testfragmentstate E/rexih: MainFragment onHiddenChanged:true
2019-08-16 15:19:25.074 30945-30945/cn.rexih.android.testfragmentstate E/rexih: SecondFragment onHiddenChanged:false
2019-08-16 15:19:25.074 30945-30945/cn.rexih.android.testfragmentstate E/rexih: SecondFragment onStart
2019-08-16 15:19:25.074 30945-30945/cn.rexih.android.testfragmentstate E/rexih: MainFragment onStart
2019-08-16 15:19:25.076 30945-30945/cn.rexih.android.testfragmentstate E/rexih: SecondFragment onResume
```
