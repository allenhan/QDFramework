package org.qd4j.qdframework.util;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ClassUtil {

	/**
	 * 获取类加载器
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	// load class
	public static Set<Class<?>> getClassSet(String packgname) {
		Set<Class<?>> clSet = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(
					packgname.replace(".", "/"));
			while (urls.hasMoreElements()) {
				URL url = (URL) urls.nextElement();
				if (url != null) {
					String protocal = url.getProtocol();
					String packagePath = url.getPath().replaceAll("%20", " ");
					if (protocal.equals("file")) {
						addClass(clSet, packagePath, packgname);
					} else if (protocal.equals(".jar")) {
						JarURLConnection connection = (JarURLConnection) url
								.openConnection();
						if (connection != null) {
							JarFile jarFile = connection.getJarFile();
							Enumeration<JarEntry> jarEntitys = jarFile
									.entries();
							while (jarEntitys.hasMoreElements()) {
								JarEntry jarEntry = (JarEntry) jarEntitys
										.nextElement();
								String jarEntityName = jarEntry.getName();
								if (jarEntityName.endsWith(".class")) {
                                   String className=jarEntityName.substring(0,jarEntityName.lastIndexOf(".")).replaceAll("/", ".");
                                   doAddClass(clSet,className);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return clSet;
	}

	private static void addClass(Set<Class<?>> setclass, String packgePath,
			String packgeName) {
		File[] files = new File(packgePath).listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return (pathname.isFile() && pathname.getName().endsWith(
						".class"))
						|| pathname.isDirectory();
			}
		});
		for (File fl : files) {
			String fileName = fl.getName();
			if (fl.isFile()) {
				String className = fileName.substring(0,
						fileName.lastIndexOf(','));
				className = packgeName + "." + className;
				doAddClass(setclass, className);
			} else {
				String subFilePath = packgePath + "/" + fileName;
				String subPackage = packgePath + "." + fileName;
				addClass(setclass, subFilePath, subPackage);
			}
		}
	}

	private static void doAddClass(Set<Class<?>> setclass, String className) {
		Class<?> cls = loadClass(className, false);
		setclass.add(cls);
	}

	public static Class<?> loadClass(String className, Boolean isInit) {
		Class<?> cls = null;
		try {
			cls = Class.forName(className, isInit, getClassLoader());
		} catch (Exception e) {

		}
		return cls;
	}
}
