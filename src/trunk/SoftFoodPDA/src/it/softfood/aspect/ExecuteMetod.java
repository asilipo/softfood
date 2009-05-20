package it.softfood.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class ExecuteMetod {

	public synchronized static Object invoke(Object target, String methodName, Object[] params) {
		try {	 
			Method m = findMethod(target.getClass().getDeclaredMethods(), methodName, params);
			if (m == null) {
				m = findMethod(target.getClass().getMethods(), methodName, params);
			}

			return m.invoke(target, params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		} 
	}

	public static Method findMethod(Method[] methods, String name, Object[] params) {
		for (int i = 0; i < methods.length; i++) {
			if (compareMethod(methods[i], name, params)) {
				return methods[i];
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static boolean compareMethod(Method method, String name, Object[] params) {
		if (! name.equals(method.getName()) || method.getParameterTypes().length != params.length) {
			return false;
		}

		Class[] types = method.getParameterTypes();
		for(int i = 0; i < types.length; i++) {
			if (! isCompatible(types[i], params[i].getClass())) {
				return false;
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public static boolean isCompatible(Class type, Class param) {
		if (type.equals(param) ||
				toPrimitive(type).equals(toPrimitive(param)) ||
				checkSuperTypes(type, param)) {

			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	protected static Class toPrimitive(Class type) {
		if (type.getName().equals("java.lang.Integer")) {
			return int.class;
		} else if (type.getName().equals("java.lang.Char")) {
			return char.class;
		} else if (type.getName().equals("java.lang.Byte")) {
			return byte.class;
		} else if (type.getName().equals("java.lang.Short")) {
			return short.class;
		} else if (type.getName().equals("java.lang.Long")) {
			return long.class;
		} else if (type.getName().equals("java.lang.Float")) {
			return float.class;
		} else if (type.getName().equals("java.lang.Double")) {
			return double.class;
		} else if (type.getName().equals("java.lang.Boolean")) {
			return boolean.class;
		}

		return type;
	}

	@SuppressWarnings("unchecked")
	public static boolean checkSuperTypes(Class type, Class param) {
		if (! type.equals(Object.class)) {
			if (type.equals(param)) {
				return true;
			}
			return checkSuperTypes(type, param.getSuperclass());
		}
		return false;
	}

}
