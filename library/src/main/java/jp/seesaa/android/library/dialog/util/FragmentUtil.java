package jp.seesaa.android.library.dialog.util;

import androidx.fragment.app.Fragment;

public class FragmentUtil {

    @SuppressWarnings("unchecked")
    public static <T> T registeredListener(Fragment fragment, Class<?> instanceClazz) throws ClassNotFoundException {
        T listener;

        if (instanceClazz.isInstance(fragment.getTargetFragment())) {
            listener = (T) fragment.getTargetFragment();
            return listener;
        }
        if (instanceClazz.isInstance(fragment.getParentFragment())) {
            listener = (T) fragment.getParentFragment();
            return listener;
        }
        if (instanceClazz.isInstance(fragment.getActivity())) {
            listener = (T) fragment.getActivity();
            return listener;
        }

        throw new ClassNotFoundException(
                String.format("%s is not found at %s", instanceClazz.getCanonicalName(), fragment.getClass().getCanonicalName())
        );
    }

}
