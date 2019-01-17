package controllers;

import java.util.ArrayList;

import javafx.scene.Scene;

/**
 * 
 * @author Adam Mahameed
 * @version 1.4 [16.1.2019]
 * 
 */
public class SceneController {
	private static ArrayList<Scene> stageList = new ArrayList<>();

	public static Scene pop() {
		Scene scene = stageList.get(0);
		stageList.remove(0);
		return scene;
	}

	public static void push(Scene scene) {
		stageList.add(0, scene);
	}
}
