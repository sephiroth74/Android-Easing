Android-Easing
==============

Light weight android easing library.


## Setup
Just add the following line to your `dependencies` group:

    compile 'it.sephiroth.android.library.easing:library:+'

> In this way you'll always use the latest version


## Example usage:

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
    
	// start the easing from 0 to 100 
	// using Cubic easeOut
	// and a duration of 500ms
    manager.start(Cubic.class, EaseType.EaseOut, 0, 100, 500);




There are different easing classes you can use:
* Back
* Bounce
* Circ
* Cubic
* Elastic
* Expo
* Linear
* Quad
* Quart
* Quint
* Sine