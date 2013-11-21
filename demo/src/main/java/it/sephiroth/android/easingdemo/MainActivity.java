package it.sephiroth.android.easingdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import it.sephiroth.android.library.easing.*;

public class MainActivity extends Activity implements View.OnClickListener {

	static final String LOG_TAG = "demo";

	ViewGroup container;
	Class<? extends Easing>[] classes;

	EasingManager.EaseType mEaseType = EasingManager.EaseType.EaseOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		container = (ViewGroup) findViewById(R.id.container);
		classes = new Class[]{
				Back.class, Bounce.class, Circ.class, Cubic.class, Elastic.class, Expo.class, Linear.class, Quad.class, Quart.class, Quint.class, Sine.class
		};

		for (Class<? extends Easing> clazz : classes) {
			Button button = new Button(this);
			button.setText(clazz.getSimpleName());
			button.setTag(clazz);
			button.setOnClickListener(this);

			ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(300, ActionBar.LayoutParams.WRAP_CONTENT);
			container.addView(button, lp);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.ease_in) {
			mEaseType = EasingManager.EaseType.EaseIn;
			return true;
		} else if (id == R.id.ease_out) {
			mEaseType = EasingManager.EaseType.EaseOut;
			return true;
		} else if (id == R.id.ease_in_out) {
			mEaseType = EasingManager.EaseType.EaseInOut;
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		switch (mEaseType) {
			case EaseIn:
				menu.findItem(R.id.ease_in).setChecked(true);
				menu.findItem(R.id.ease_out).setChecked(false);
				menu.findItem(R.id.ease_in_out).setChecked(false);
				break;

			case EaseOut:
				menu.findItem(R.id.ease_in).setChecked(false);
				menu.findItem(R.id.ease_out).setChecked(true);
				menu.findItem(R.id.ease_in_out).setChecked(false);
				break;

			case EaseInOut:
				menu.findItem(R.id.ease_in).setChecked(false);
				menu.findItem(R.id.ease_out).setChecked(false);
				menu.findItem(R.id.ease_in_out).setChecked(true);
				break;
		}

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) {

		double finalX = container.getWidth() - v.getWidth() - container.getPaddingLeft() - container.getPaddingRight();

		makeEasing(v, finalX);
	}

	void makeEasing(final View view, final double finalX) {

		final double mFinalX;
		final double mStartX;

		final float current = view.getTranslationX();

		if (current > 0) {
			mFinalX = 0;
			mStartX = current;
		} else {
			mFinalX = finalX;
			mStartX = 0;
		}

		EasingManager manager = new EasingManager(new EasingManager.EasingCallback() {
			@Override
			public void onEasingValueChanged(double value, double oldValue) {
				view.setTranslationX((float) (value));
			}

			@Override
			public void onEasingStarted(double value) {
				view.setTranslationX((float) mStartX);
			}

			@Override
			public void onEasingFinished(double value) {
				view.setTranslationX((float) mFinalX);
			}
		});
		manager.start((Class<? extends Easing>) view.getTag(), mEaseType, mStartX, mFinalX, 500);

	}
}
