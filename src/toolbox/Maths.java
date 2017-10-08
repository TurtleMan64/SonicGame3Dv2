package toolbox;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import entities.Camera;

public class Maths 
{
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) 
	{
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
		return matrix;
	}
	
	public static float barryCentric(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos) 
	{
		float det = (p2.z - p3.z) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.z - p3.z);
		float l1 = ((p2.z - p3.z) * (pos.x - p3.x) + (p3.x - p2.x) * (pos.y - p3.z)) / det;
		float l2 = ((p3.z - p1.z) * (pos.x - p3.x) + (p1.x - p3.x) * (pos.y - p3.z)) / det;
		float l3 = 1.0f - l1 - l2;
		return l1 * p1.y + l2 * p2.y + l3 * p3.y;
	}
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, 
			float ry, float rz, float scale)
	{
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);	
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale,scale,scale), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0 ,0), viewMatrix,
				viewMatrix);
		Matrix4f.rotate( (float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		Matrix4f.translate(negativeCameraPos,  viewMatrix,  viewMatrix);
		return viewMatrix;
	}
	
	public static int sign(float value)
	{
		if(value > 0)
		{
			return 1;
		}
		else
		{
			if(value < 0)
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}
	
	public static Vector4f multiplyByMat(Vector4f pos, Matrix4f mat)
	{
		Vector4f returnVec = new Vector4f(0,0,0,0);
		
		returnVec.x = pos.x*mat.m00+pos.y*mat.m01+pos.z*mat.m02+pos.w*mat.m03;
		returnVec.y = pos.x*mat.m10+pos.y*mat.m11+pos.z*mat.m12+pos.w*mat.m13;
		returnVec.z = pos.x*mat.m20+pos.y*mat.m21+pos.z*mat.m22+pos.w*mat.m23;
		returnVec.w = pos.x*mat.m30+pos.y*mat.m31+pos.z*mat.m32+pos.w*mat.m33;
		
		return returnVec;
	}
	
	
	public static Vector3f mapInputs3(float angle, float mag, Vector3f VecC)
	{
		angle = angle % (float)(Math.PI*2);
		float tempx = (float)Math.cos(angle)*mag;
		float tempz = (float)Math.sin(angle)*mag;
		
		float CDir = (float)Math.atan2(VecC.z, VecC.x);
		CDir+=Math.PI/2;
		float Cx = (float)Math.cos(CDir);
		float Cz = (float)Math.sin(CDir);
		
		float CDist = (float)Math.sqrt(VecC.x*VecC.x+VecC.z*VecC.z);
		float CPitch = (float)(Math.PI/2+Math.atan2(VecC.y, CDist));
		
		double[] result = RotationMatrixUnnormalizedDirVector.rotPointFromFormula(0, 0, 0, Cx, 0, Cz, tempx, 0, tempz, CPitch);
		Vector3f res = new Vector3f((float)result[0], (float)result[1], (float)result[2]);
		return res;
	}
	
	/**
	 * @param initialVelocity
	 * @param surfaceNormal
	 * @param elasticity Scale of the resulting vector relative to the original velocity
	 */
	public static Vector3f bounceVector(Vector3f initialVelocity, Vector3f surfaceNormal, float elasticity)
	{
		Vector3f twoNtimesVdotN = new Vector3f(surfaceNormal);
		twoNtimesVdotN.scale(-2*Vector3f.dot(initialVelocity, surfaceNormal));
		
		Vector3f Vnew = Vector3f.add(twoNtimesVdotN, initialVelocity, null);
		Vnew.scale(elasticity);
		
		return Vnew;
	}
	
	public static Vector3f wallSlide(Vector3f velocity, Vector3f normal)
	{
		Vector3f unitNorm = new Vector3f();
		normal.normalise(unitNorm);
		
		float a1 = Vector3f.dot(velocity, unitNorm);
		
		Vector3f v1 = new Vector3f(unitNorm);
		v1.scale(a1);
		
		Vector3f v2 = new Vector3f();
		
		Vector3f.sub(velocity, v1, v2);
		
		return v2;
	}
	
	/** Returns the point on a sphere that has the given angles from the center
	 * @param angH in radians
	 * @param angV in radians
	 * @param radius
	 * @return
	 */
	public static Vector3f spherePositionFromAngles(float angH, float angV, float radius)
	{
		float y = (float)(radius*Math.sin(angV));
		float hpt = (float)(radius*Math.cos(angV));
		
		float x = (float)(hpt*Math.cos(angH));
		float z = (float)(hpt*Math.sin(angH));
		
		return new Vector3f(x, y, z);
	}
}
