package com.crossbridge.kernel.spring.annotation;

@Person(name="xingoo",age=25)
public class Test3 {
	@ModuleReference(filter = "abc", id = "test3.abc", targetName = "com.crossbridge.kernel.spring.annotation.ContextServiceScannerParser")
	public String msg;
	 public void print(Class c){
		 System.out.println(c.getName());
		         
		         //java.lang.Class的getAnnotation方法，如果有注解，则返回注解。否则返回null
		         Person person = (Person)c.getAnnotation(Person.class);
		         
		         if(person != null){
		             System.out.println("name:"+person.name()+" age:"+person.age());
		         }else{
		             System.out.println("person unknown!");
		         }
		     }
		     public static void main(String[] args){
		    	 Test3 t = new Test3();
		         t.print(t.getClass());
		


	 }
}
