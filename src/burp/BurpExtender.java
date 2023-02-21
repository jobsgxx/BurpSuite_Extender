package burp;

import UI.BurpGUI;
import javax.swing.*;
import java.awt.*;

public class BurpExtender implements IBurpExtender,ITab{
	private JPanel jPanelMain;

	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		BurpGUI UI = BurpGUI.getburpGUI();
		jPanelMain = UI.root;
		callbacks.customizeUiComponent(jPanelMain);
		callbacks.printOutput("AES Tools Test!!!");

		callbacks.addSuiteTab(this);
	}

	@Override
	public String getTabCaption() {
		return "AESTools";
	}

	@Override
	public Component getUiComponent() {
		return jPanelMain;
	}
}