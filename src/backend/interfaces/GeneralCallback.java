package backend.interfaces;

public interface GeneralCallback {
	default void onPressOk() {};
	default void onGetObject(Object object) {};
}
