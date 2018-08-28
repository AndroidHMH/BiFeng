package com.coinwind.bifeng.base;

/**
 * 统一管理Presenter
 * @param <T>
 */
public interface BasePresenter<T> {
    void actualView(T view);

    void unActualView();
}
