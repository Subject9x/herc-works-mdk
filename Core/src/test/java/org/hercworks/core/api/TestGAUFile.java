package org.hercworks.core.api;

import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.hercworks.core.data.file.gau.HButtonBasic;
import org.hercworks.core.data.file.gau.HPanel;
import org.hercworks.core.data.file.gau.HWeaponPanel;
import org.hercworks.core.data.file.gau.HWeaponPanelItem;
import org.hercworks.core.data.file.gau.HWidgetId;
import org.hercworks.core.data.file.gau.WidgetBase;
import org.hercworks.core.io.transform.ThreeSpaceByteTransformer;
import org.hercworks.voln.DataFile;
import org.junit.Test;

public class TestGAUFile extends ThreeSpaceByteTransformer{

	
	@Test
	public void printout() throws IOException{
		
		File file = new File("e://es2_os/dev/earthsiege2/unpack/simvol0/gau/tomahawk.gau");
		
		assertTrue(file.isFile());
		
		FileInputStream fizz = new FileInputStream(file);
		
		setBytes(fizz.readAllBytes());
		
		fizz.close();
		
		HPanel panelRoot = new HPanel(new Point(indexIntLE(),indexIntLE()), new Dimension(indexIntLE(), indexIntLE()), new Point(0,0));
		panelRoot.sethWidgetId(HWidgetId.ROOTPANEL);
		System.out.println(panelRoot);
		
		//Weapon Panel root
		HWeaponPanel wpnPanel = new HWeaponPanel(new Point(0,0), new Dimension(0,0));
		wpnPanel.setActiveTotal(indexIntLE());
		
		for(int i=0; i < 10; i++) {
			HWeaponPanelItem item = new HWeaponPanelItem(new Point(indexIntLE(), indexIntLE()), new Dimension(indexIntLE(), indexIntLE()));
			item.sethWidgetId(HWidgetId.fromId(i+1));
			wpnPanel.getComponents()[i] = item; 
		}
		System.out.println(wpnPanel);
		
		skip(288);
		
		HPanel chainLinkTrackPnl = new HPanel(new Point(indexIntLE(),indexIntLE()), new Dimension(indexIntLE(), indexIntLE()), new Point(0,0));
		chainLinkTrackPnl.setComponents(new WidgetBase[3]);
		chainLinkTrackPnl.sethWidgetId(HWidgetId.LINKCHAIN_PANEL);
		
		chainLinkTrackPnl.getComponents()[0] = new HButtonBasic(new Point(indexIntLE(),indexIntLE()), new Point(indexIntLE(), indexIntLE()));
		chainLinkTrackPnl.getComponents()[0].sethWidgetId(HWidgetId.WPN_CHAIN_BTN);
		
		chainLinkTrackPnl.getComponents()[1] = new HButtonBasic(new Point(indexIntLE(),indexIntLE()), new Point(indexIntLE(), indexIntLE()));
		chainLinkTrackPnl.getComponents()[1].sethWidgetId(HWidgetId.WPN_LINK_BTN);
		
		chainLinkTrackPnl.getComponents()[2] = new HButtonBasic(new Point(indexIntLE(),indexIntLE()), new Point(indexIntLE(), indexIntLE()));
		chainLinkTrackPnl.getComponents()[2].sethWidgetId(HWidgetId.AUT_TRACK_BTN);
		System.out.println(chainLinkTrackPnl);
		
		
		
	}

	@Override
	public DataFile bytesToObject(byte[] inputArray) throws ClassCastException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] objectToBytes(DataFile source) throws ClassCastException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
