Android-Easing
==============

Light weight android easing.

Example usage:

    import it.sephiroth.android.library.easing.*;

    EasingManager manager = new EasingManager(new EasingManager.EasingCallback() {
        @Override
        public void onEasingValueChanged(double value, double oldValue) {
        }

        @Override
        public void onEasingStarted(double value) {
        }

        @Override
        public void onEasingFinished(double value) {
        }
    });
    manager.start(Cubic.class, EaseType.EaseOut, 0, 100, 500);


