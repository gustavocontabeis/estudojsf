/**
 * 
 */
package br.com.codersistemas.uteis;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import br.com.codersistemas.annotations.Label;
import br.com.codersistemas.exceptions.ReflectionUtilsException;
import br.com.codersistemas.model.entity.Pessoa;

public class ReflectionUtils {
	
	private ReflectionUtils() {
		
	}

	public static void printGettersSetters(Class aClass) {
		Method[] methods = aClass.getMethods();
		for (Method method : methods) {
			if(method.getName().equals("equals") || method.getName().equals("getClass")) {
				continue;
			}
			if (isGetter(method))
				System.out.println("getter: " + method);
			if (isSetter(method))
				System.out.println("setter: " + method);
		}
	}

	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get")) {
			return false;
		}
		if (method.getParameterTypes().length != 0) {
			return false;
		}
		if (void.class.equals(method.getReturnType())) {
			return false;
		}
		return true;
	}

	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set")) {
			return false;
		}
		if (method.getParameterTypes().length != 1) {
			return false;
		}
		return true;
	}

	/**
	 * Retorna o Field mesmo com herança
	 * @param obj obj
	 * @param nome nome
	 * @return field field
	 */
	public static Field getField(Object obj, String nome) {
		Class classe = obj.getClass();
		do {
			Field[] declaredFields = classe.getDeclaredFields();
			for (Field field : declaredFields) {
				if (field.getName().equals(nome)) {
					return field;
				}
			}
			classe = classe.getSuperclass();
		} while (classe != Object.class);
		return null;
	}

	public static Field[] getFields(Class classe) {
		List<Field> list = new ArrayList<Field>();
		do {
			Field[] declaredFields = classe.getDeclaredFields();
			for (Field field : declaredFields) {
				if (!field.getName().equals("serialVersionUID"))
					list.add(field);
			}
			classe = classe.getSuperclass();
		} while (classe != Object.class);
		return list.toArray(new Field[list.size()]);
	}

	public static Field[] getFields(Object obj) {
		return getFields(obj.getClass());
	}

	@Deprecated
	public static Annotation[] getAnnotations(Object obj, String name) {
		List<Annotation> list = new ArrayList<>();
		Field field = getField(obj, name);
		for (Annotation a : field.getDeclaredAnnotations()) {
			list.add(a);
		}
		Method getter = getGetter(obj.getClass(), field);// XXXXXXXXXXXXXXXXXXX
		for (Annotation a : getter.getDeclaredAnnotations()) {
			list.add(a);
		}
		Method setter = getSetter(obj.getClass(), field);// XXXXXXXXXXXXXXXXXXX
		for (Annotation a : setter.getDeclaredAnnotations()) {
			list.add(a);
		}
		return list.toArray(new Annotation[list.size()]);
	}

	private static Method getSetter(Class classe, Field field) {
		String seterName = getSeterName(field.getName());
		Method[] methods = getMethods(classe);
		for (Method method : methods) {
			if (seterName.equals(method.getName())) {
				return method;
			}
		}
		return null;
	}

	private static Method getGetter(Class obj, Field field) {
		String geterName = getGeterName(field.getName());
		Method[] methods = getMethods(obj);
		for (Method method : methods) {
			if (geterName.equals(method.getName())) {
				return method;
			}
		}
		
		geterName = getGeterNameBoolean(field.getName());
		for (Method method : methods) {
			if (geterName.equals(method.getName())) {
				return method;
			}
		}
		
		return null;
	}

	public static Method getMethod(Class classe, String name) {
		do {
			Method[] methodFields = classe.getDeclaredMethods();
			for (Method method : methodFields) {
				if (method.getName().equals(name)) {
					return method;
				}
			}
			classe = classe.getSuperclass();
		} while (classe != Object.class);
		return null;
	}

	public static Method[] getMethods(Class classe) {
		Class classe1 = classe;
		List<Method> list = new ArrayList<Method>();
		do {
			for (Method method : classe1.getDeclaredMethods()) {
				list.add(method);
			}
			classe1 = classe1.getSuperclass();
		} while (classe1 != Object.class);
		return list.toArray(new Method[list.size()]);
	}

	public static Annotation[] getAnnotation(Class classe, Field field) {
		List<Annotation> list = new ArrayList<Annotation>();
		for (Annotation annotation : field.getDeclaredAnnotations()) {
			list.add(annotation);
		}
		Method getter = getGetter(classe, field);
		if (getter != null) {
			for (Annotation annotation : getter.getDeclaredAnnotations()) {
				list.add(annotation);
			}
		}

		Method setter = getSetter(classe, field);
		if (setter != null) {
			for (Annotation annotation : setter.getDeclaredAnnotations()) {
				list.add(annotation);
			}
		}
		return list.toArray(new Annotation[list.size()]);
	}

	/**
	 * id - getId, nome - getNome
	 * @param name name
	 * @return String String
	 */
	public static String getGeterName(String name) {
		if (name.length() >= 2) {
			return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
		} else {
			return name;
		}
	}

	public static String getGeterNameBoolean(String name) {
		if (name.length() >= 2) {
			return "is" + name.substring(0, 1).toUpperCase() + name.substring(1);
		} else {
			return name;
		}
	}

	/**
	 * id - setId, nome - setNome
	 * @param name name
	 * @return String String
	 */
	public static String getSeterName(String name) {
		if (name.length() >= 2) {
			return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
		} else {
			return name;
		}
	}

	public static Annotation getAnnotation(Class classe, Field field, Class<? extends Annotation> annotationClass) {
		for (Annotation annotation : getAnnotation(classe, field)) {
			if (annotation.annotationType() == annotationClass) {
				return annotation;
			}
		}
		return null;
	}

	public static Object getValor(Object obj, String atributo) {

		Field field = getField(obj, atributo);
		if (field == null)
			throw new ReflectionUtilsException("A classe " + obj.getClass().getName() + " não possui o atributo " + atributo);

		Method getter = getGetter(obj.getClass(), field);
		if (getter == null)
			return null;

		Object invoke = null;
		try {
			invoke = getter.invoke(obj, new Class[] {});
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return invoke;
	}

	public static MudancaConteudoDTO[] buscarDiferencas(Object obj1, Object obj2) {

		List<MudancaConteudoDTO> list = new ArrayList<>();
		Field[] fields = getFields(obj1.getClass());
		
		for (Field field : fields) {

			Object valor1 = getValor(obj1, field.getName());
			Object valor2 = getValor(obj2, field.getName());
			
			if(valor1 == null && valor2 == null) {
				continue;
			}
			
			if(valor1 != null && valor1 instanceof Date) {
				valor1 = DateUtils.dateTimeToString((Date) valor1);
			}
			
			if(valor2 != null && valor2 instanceof Date) {
				valor2 = DateUtils.dateTimeToString((Date) valor2);
			}
			
			String label = field.getName();
			
			Annotation annotation = getAnnotation(obj1.getClass(), field, Label.class);
			if(annotation != null) {
				Label annotationLabel = (Label) annotation;
				label = annotationLabel.name();
			}
			
			Class classe = valor1 != null ? valor1.getClass() : valor2.getClass() ;
			if(!classe.isEnum() && !classe.getName().startsWith("java.")) {
				Field[] fields2 = getFields(obj1.getClass());
				for (Field field2 : fields2) {
					Annotation annotationId = getAnnotation(obj1.getClass(), field2, Id.class);
					if(annotationId != null) {
						
						if(valor1 != null)
							valor1 = getValor(valor1, field2.getName());
						
						if(valor2 != null)
							valor2 = getValor(valor2, field2.getName());
						
						break;
					}
				}
			}

			if (valor1 != null) {
				if (!valor1.equals(valor2)) {
					list.add(MudancaConteudoDTO.builder().noCampo(label).coAntes(valor1.toString()).coDepois(valor2 != null ? valor2.toString() : null).build());
				}
			} else {
				if(valor2 != null) {
					list.add(MudancaConteudoDTO.builder().noCampo(label).coAntes((valor1 != null ? valor1.toString() : null)).coDepois(valor2 != null ? valor2.toString() : null).build());
				}
			}
		}

		return list.toArray(new MudancaConteudoDTO[list.size()]);
	}
	
	public static void inject(Object action, String nomeSAtributo, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		setValue(action, nomeSAtributo, value);
	}
		
	public static void setValue(Object action, String nomeSAtributo, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field fieldGitecsPermitidas = null;;
		Field[] fields = getFields(action.getClass());
		for (Field field : fields) {
			if(field.getName().equals(nomeSAtributo)) {
				fieldGitecsPermitidas = field;
				break;
			}
		}
		if(fieldGitecsPermitidas == null)
			throw new ReflectionUtilsException("O atribito "+nomeSAtributo+" não existe.");
		fieldGitecsPermitidas.setAccessible(true);
		fieldGitecsPermitidas.set(action, value);
	}

	public static <T> T setValues(Class<T> classe, Object...args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
		
		T obj = classe.newInstance();
		
		String atribute = null;
		Object valor;
		
		for (int i = 0; i < args.length; i++) {
			if( i % 2 == 0) {
				atribute = (String) args[i];
			}else {
				valor = args[i];
				setValue(obj, atribute, valor);
			}
		}
		
		return obj;
	}

	public static Class getTipoGenericoRetorno(Method method) {
		String returnType = method.getReturnType().getSimpleName();
		if(method.getReturnType() == List.class) {
			String typeGeneric = "";
			if (method.getGenericReturnType() instanceof ParameterizedType) {
				 ParameterizedType type = (ParameterizedType) method.getGenericReturnType();
				 Type[] actualTypeArguments = type.getActualTypeArguments();
				 for (Type type2 : actualTypeArguments) {
					Class c = (Class) type2;
					typeGeneric = c.getSimpleName();
					return c;
				}
			}
		}
		return null;
	}

	public static String getTipoGenericoRetornoString(Method method) {
		String returnType = method.getReturnType().getSimpleName();
		if(method.getReturnType() == List.class) {
			String typeGeneric = "";
			if (method.getGenericReturnType() instanceof ParameterizedType) {
				 ParameterizedType type = (ParameterizedType) method.getGenericReturnType();
				 Type[] actualTypeArguments = type.getActualTypeArguments();
				 for (Type type2 : actualTypeArguments) {
					Class c = (Class) type2;
					typeGeneric = c.getSimpleName();
				}
			}
			returnType = method.getReturnType().getSimpleName()+"<"+typeGeneric+">";
		}
		return returnType;
	}

	public static boolean getContemRetorno(Method method) {
		return method.getGenericReturnType() != void.class;
	}
	
	public static Object getValorLiteral(Object obj, String atributo) {
		Object valor = getValor(obj, atributo);
		
		if(valor != null) {
			if ( valor instanceof String ) {
				return "\"" + valor + "\"";
			}
			
			if ( valor instanceof Integer ) {
				return valor.toString();
			}
			
			if ( valor instanceof Long ) {
				return valor.toString()+"L";
			}
			
			if ( valor instanceof Float ) {
				return valor.toString()+"F";
			}
			
			if ( valor instanceof Double ) {
				return valor.toString();
			}
			
			if ( valor instanceof Date ) {
				return valor.toString();
			}
			
			if ( valor instanceof Enum ) {
				return valor.getClass().getSimpleName() + "." + valor.toString();
			}
			
			if ( valor instanceof Short ) {
				return "(short) "+valor.toString();
			}
			
			if ( valor instanceof Boolean ) {
				return valor.toString();
			}
			
		} else {
			return "null";
		}
		
		return null;
	}

	public static <T> T clone(T obj1) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
		T obj2 = (T) obj1.getClass().newInstance();
		Field[] fields = ReflectionUtils.getFields(obj1.getClass());
		for (Field field : fields) 
			ReflectionUtils.setValue(obj2, field.getName(), ReflectionUtils.getValor(obj1, field.getName()));
		return obj2;
	}
	
	public static <T> T cloneEntity(T obj1) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
		T obj2 = (T) obj1.getClass().newInstance();
		Field[] fields = ReflectionUtils.getFields(obj1.getClass());
		for (Field field : fields) {
			
			Annotation annotationColumn = getAnnotation(obj1.getClass(), field, Column.class);
			Annotation annotationId = getAnnotation(obj1.getClass(), field, Id.class);
			
			if(annotationId != null) {
				continue;
			} else if(annotationColumn != null) {
				Column column = (Column) annotationColumn;
				if(column.unique()) {
					continue;
				}else {
					ReflectionUtils.setValue(obj2, field.getName(), ReflectionUtils.getValor(obj1, field.getName()));
				}
			} else {
				ReflectionUtils.setValue(obj2, field.getName(), ReflectionUtils.getValor(obj1, field.getName()));
			}
			
		}
		return obj2;
	}


	public static String toString(Object obj1) {
		Field[] fields = getFields(obj1.getClass());
		
		StringBuilder sb = new StringBuilder();
		
		for (Field field : fields) {
			
			Annotation annotation = getAnnotation(obj1.getClass(), field, Label.class);
			String nome = null;
			if(annotation != null) {
				Label label = (Label) annotation;
				nome = label.name();
			} else {
				nome = field.getName();
			}
			
			sb.append(nome);
			sb.append("=");
			
			Object valor = getValor(obj1, field.getName());
			
			sb.append(valor != null ? valor : "");
			sb.append(",");
			
		}
		return sb.toString();
	}

	public static Object toStringNotNulls(Object obj1, String...ignores) {
		
		Field[] fields = getFields(obj1.getClass());
		
		StringBuilder sb = new StringBuilder();
		
		for (Field field : fields) {
			
			for(String ignore : ignores) {
				if(ignore.equals(field.getName())) {
					continue;
				}
			}
			
			Annotation annotation = getAnnotation(obj1.getClass(), field, Label.class);
			String nome = null;
			if(annotation != null) {
				Label label = (Label) annotation;
				nome = label.name();
			} else {
				nome = field.getName();
			}
			
			Object valor = getValor(obj1, field.getName());
			
			if(valor != null && !valor.equals("")) {
				sb.append(nome);
				sb.append("=");
				sb.append(valor);
				sb.append(",");
			}
			
		}
			
		return sb.toString();
	}

}
