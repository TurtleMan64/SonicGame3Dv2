package fontRendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engineTester.MainGameLoop;
import renderEngine.Loader;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontMeshCreator.TextMeshData;

public class TextMaster
{
	private static Loader loader;
	private static Map<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
	private static FontRenderer renderer;
	
	private TextMaster() {}

	public static void init()
	{
		renderer = new FontRenderer();
		loader = MainGameLoop.gameLoader;
	}
	
	public static void render()
	{
		renderer.render(texts);
	}
	
	public static void loadText(GUIText text)
	{
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int[] vertexObjects = loader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		int vao = vertexObjects[0];
		int[] vbos = new int[2];
		vbos[0] = vertexObjects[1];
		vbos[1] = vertexObjects[2];
		text.setMeshInfo(vao, vbos, data.getVertexCount());
		List<GUIText> textBatch = texts.get(font);
		if (textBatch == null)
		{
			textBatch = new ArrayList<GUIText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	
	public static void removeText(GUIText text)
	{
		List<GUIText> textBatch = texts.get(text.getFont());
		textBatch.remove(text);
		if (textBatch.isEmpty())
		{
			texts.remove(text.getFont());
			
		}
		//TODO: remove vao and vbo
	}
	
	public static void cleanUp()
	{
		renderer.cleanUp();
	}
}
