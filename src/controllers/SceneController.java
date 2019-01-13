package controllers;

import java.util.ArrayList;

import javafx.scene.Scene;

/**
 * 
 * @author Adam Mahameed
 * @version 1.2 [12.1.2019]
 * 
 */
public class SceneController {
	private static ArrayList<Scene> stageList = new ArrayList<>();

	public static Scene pop() {
		return stageList.get(0);
	}

	public static void push(Scene scene) {
		stageList.add(scene);
	}
}
