package jp.idumo.android.core.exec;

import jp.idumo.android.core.AndroidActivityResource;
import jp.idumo.android.core.AndroidController;
import jp.idumo.core.exception.IDUMOException;
import jp.idumo.core.exception.IDUMORuntimeException;
import jp.idumo.core.exec.CoreActivity;
import jp.idumo.core.exec.CoreController;
import jp.idumo.core.parts.Executable;
import android.os.Handler;

public class AndroidActivity implements CoreActivity, Runnable {
	
	private AndroidComponent	component;
	private Handler				handler	= new Handler();
	
	public AndroidActivity(AndroidComponent component) {
		this.component = component;
		this.component.setContainer(new AndroidContainer());
	}
	
	@Override
	public void onIdumoCreated() throws IDUMOException {
		component.onIdumoMakeFlowChart();
		component.setup();
		component.onIdumoPrepare();
	}
	
	@Override
	public void onIdumoExec() throws IDUMORuntimeException {
		while (!component.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		Executable runnable = component.getRunnable();
		while (!runnable.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		int count = component.getLoopCount();
		if (count == -1) {
			while (true) {
				if (runnable.isReady()) {
					handler.post(runnable);
				}
				try {
					Thread.sleep(component.getSleepTime());
				} catch (InterruptedException e) {}
			}
		}
		for (int i = 0; i < count;) {
			if (runnable.isReady()) {
				handler.post(runnable);
				i++;
			}
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
			
		}
	}
	
	@Override
	public void onIdumoStart() {
		for (AndroidController controller : component.getAndroidControllers()) {
			controller.onIdumoStart();
			controller.onIdumoResume();
		}
		for (CoreController controller : component.getApplicationControllers()) {
			controller.onIdumoStart();
		}
		component.setReady(true);
	}
	
	@Override
	public void onIdumoStop() {
		for (AndroidController controller : component.getAndroidControllers()) {
			controller.onIdumoPause();
			controller.onIdumoStop();
		}
		for (CoreController controller : component.getApplicationControllers()) {
			controller.onIdumoStop();
		}
		component.setReady(false);
	}
	
	@Override
	public void run() {
		onIdumoExec();
	}
	
	public void setActivity(AndroidActivityResource activity) {
		component.setActivity(activity);
	}
	
}
