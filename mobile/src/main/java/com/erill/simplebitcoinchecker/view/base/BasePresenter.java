package com.erill.simplebitcoinchecker.view.base;

import java.lang.ref.WeakReference;

/**
 * Created by Roger on 5/12/17.
 */

public abstract class BasePresenter<V> {

    private WeakReference<V> view;

    public final void bindView(V view) {
        this.view = new WeakReference<>(view);
    }

    public void unbindView() {
        view = null;
    }

    protected final V getView() {
        return this.view.get();
    }
}
