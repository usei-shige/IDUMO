import com.hixi_hyi.idumo.common.provider.NumberProvider;
import com.hixi_hyi.idumo.console.core.exec.IDUMOConsoleWrapper;
import com.hixi_hyi.idumo.console.core.util.IDUMOConsoleLogger;
import com.hixi_hyi.idumo.console.receiptor.ConsoleViewReceiptor;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.CoreComponent;
import com.hixi_hyi.idumo.core.util.LogManager;

public class NumberProviderTest extends IDUMOConsoleWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new CoreComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				NumberProvider idumo0 = new NumberProvider(10.0);
				add(idumo0);
				ConsoleViewReceiptor idumo1 = new ConsoleViewReceiptor();
				add(idumo1);
				
				connect(idumo0, idumo1);
				
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
		NumberProviderTest main = new NumberProviderTest();
		main.exec();
	}
}
