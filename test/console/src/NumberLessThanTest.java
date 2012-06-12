import com.hixi_hyi.idumo.common.handler.raw.NumberGetValueHandler;
import com.hixi_hyi.idumo.common.handler.raw.NumberLessThanHandler;
import com.hixi_hyi.idumo.common.handler.raw.NumberMoreThanHandler;
import com.hixi_hyi.idumo.common.provider.LivedoorWeatherProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.CoreComponent;
import com.hixi_hyi.idumo.core.util.LogManager;

public class NumberLessThanTest extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new CoreComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				LivedoorWeatherProvider idumo0 = new LivedoorWeatherProvider(63);
				add(idumo0);
				NumberGetValueHandler idumo1 = new NumberGetValueHandler("max_temp");
				add(idumo1);
				NumberLessThanHandler idumo2 = new NumberLessThanHandler(10);
				add(idumo2);
				ConsoleViewReceiptor idumoR = new ConsoleViewReceiptor();
				add(idumoR);
				
				connect(idumo0, idumo1);
				connect(idumo1, idumo2);
				connect(idumo2, idumoR);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
	
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new IDUMOConsoleLogger();
		NumberLessThanTest main = new NumberLessThanTest();
		main.exec();
	}
}
