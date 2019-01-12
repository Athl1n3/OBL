package controllers;

import java.util.ArrayList;

import javafx.scene.Scene;

public class SceneController {
	private static ArrayList<Scene> stageList = new ArrayList<>();

	public static Scene pop() {
		return stageList.get(0);
	}

	public static void push(Scene scene) {
		stageList.add(scene);
	}
}
