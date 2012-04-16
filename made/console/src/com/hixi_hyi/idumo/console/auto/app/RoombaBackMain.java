package com.hixi_hyi.idumo.console.auto.app;
import com.hixi_hyi.idumo.common.provider.StringProvider;
import com.hixi_hyi.idumo.common.receiptor.SendTCPReceiptor;
import com.hixi_hyi.idumo.console.exec.AbstractConsoleMain;
import com.hixi_hyi.idumo.core.exception.IDUMOException;
import com.hixi_hyi.idumo.core.exec.IDUMOComponent;
public class RoombaBackMain extends AbstractConsoleMain {
  @Override
  public void init() {
    setExecutionWithComponent(new RoombaBackComponent());
  }
  public static void main(String[] args){ 
    RoombaBackMain main = new RoombaBackMain(); 
    main.exec(); 
  }
}
class RoombaBackComponent extends IDUMOComponent {
  @Override
  public void onIdumoMakeFlowChart() throws IDUMOException {
    StringProvider s = new StringProvider("BACK60");
    add(s);
    SendTCPReceiptor r = new SendTCPReceiptor("192.168.11.4",10000);
    add(r);

    connect(s, r);
  }
  @Override
  public void onIdumoPrepare() {
    setLoopCount(1);
    setSleepTime(1000);
  }
}
