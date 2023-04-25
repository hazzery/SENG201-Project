/* 
 * InterfaceManager class was an attempt todo what was in the lab 
 * This class is a bit fucked somehow and does not do what i would like it todo
 * so i cry and try again another time.
 * However the actual UserInterfaces do run when complied seperatly
 */




public class InterfaceManager {

    public void launchMainScreen() {
		MainScreen mainWindow = new MainScreen(this);
	}
	
	public void closeMainScreen(MainScreen mainWindow) {
		mainWindow.closeWindow();
	}
	
	public void launchSetupScreen() {
		SetupScreen setupWindow = new SetupScreen(this);
		//launches SetupScreen Swift User Interface
		
	}
	
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		launchMainScreen();
	}
	
	public static void main(String[] args) {
		InterfaceManager manager = new InterfaceManager();
		manager.launchSetupScreen();
	}
}
