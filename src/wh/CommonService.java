package wh;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;

import wh.Mtom_stream_testStub;
import wh.Mtom_stream_testStub.Data;
import wh.Mtom_stream_testStub.DataSet;
import wh.Mtom_stream_testStub.Include;
import wh.Mtom_stream_testStub.PutData;
import wh.Mtom_stream_testStub.PutDataResponse;
import wh.Mtom_stream_testStub.VioVehicleInfo;

public class CommonService {

	public void send() {

		Mtom_stream_testStub stub;
		try {
			stub = new Mtom_stream_testStub();
			VioVehicleInfo vi = new VioVehicleInfo();
			vi.setHphm("123321");
			vi.setGcsj("20140922153022");
			PutData pd = new PutData();
			pd.setVio(vi);
			DataSet d = new DataSet();
			Data[] param = new Data[1];
			param[0].setContentType("image/jpeg");
			Include i = new Include();
			org.apache.axis2.databinding.types.URI fa = null;
			try {
				fa = new org.apache.axis2.databinding.types.URI("http://www.baidu.com/img/baidu_jgylogo3.gif");
			} catch (Exception e) {

			}
			i.setHref(fa);
			param[0].setInclude(i);
			d.setItem(param);
			// d.setItem(null);
			pd.setData(d);
			Data dd = new Data();
			dd.setContentType("");
			PutDataResponse prs = stub.putData(pd);
			prs.getKeys();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub

		CommonService cs = new CommonService();
		cs.send();

		// Date d = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// String s = sdf.format(d);
		// System.out.println(s);
	}
}
