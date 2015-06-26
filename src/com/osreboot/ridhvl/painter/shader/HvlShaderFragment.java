package com.osreboot.ridhvl.painter.shader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;


public class HvlShaderFragment {

	private int program;

	public HvlShaderFragment(String shaderArg){
		int shader = ARBShaderObjects.glCreateShaderObjectARB(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
		ARBShaderObjects.glShaderSourceARB(shader, readFile(shaderArg));
		ARBShaderObjects.glCompileShaderARB(shader);

		program = ARBShaderObjects.glCreateProgramObjectARB();
		ARBShaderObjects.glAttachObjectARB(program, shader);
		ARBShaderObjects.glLinkProgramARB(program);
		ARBShaderObjects.glValidateProgramARB(program);
	}

	private String readFile(String file){
		StringBuilder builder = new StringBuilder();
		try{
			FileInputStream input = new FileInputStream(file);
			BufferedReader reader = null;
			try{
				reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				String line;
				while((line = reader.readLine()) != null) builder.append(line).append('\n');
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(reader != null) reader.close();
				input.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return builder.toString();
	}

	public int getID(){
		return program;
	}
	
}
