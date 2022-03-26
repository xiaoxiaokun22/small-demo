### 自动装配的原理

### spring bean的作用域有哪些？
Singleton，每次getBean()都是同一个对象，是单例的。
Prototype，每次getBean()都会创建一个新的对象。
Request，每次http请求实例化一个bean。
Session，在一次会话中共享一个bean。
global-session，不常用
### spring mvc的工作原理是什么？
1.浏览器发送请求，会先到 DispatcherSevlet 类的doDispatch方法处理  
2.在doDispatch方法中，会调用 HandlerMapping类的getHandler(request) 方法，可以获取到对应的handler(controller)  
3.通过HandlerAdapter去执行handler，会返回ModelAndView对象，然后进行解析。
### spring mvc如何进行转发和重定向？
SpringMvc转发：forward:/路径  
SpringMvc重定向：redirect:/路径  
转发是服务器行为，只需一次跳转，用户看不到url地址上的变化。重定向是浏览器端的行为，需要进行二次跳转，用户能看到url地址上的变化。  
转发的速度比重定向的速度相对要高  
转发能够获取request作用域中的数据，因为他只发出了一次请求，而重定向无法获取 ，因为他发出了二次请求，每一次请求对应着一次新的request
### mybatis如何实现分页？分页插件原理是什么？
Mybatis使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页，而非物理分页（limit）。  
分页插件原理是在插件的拦截方法内拦截待执行的sql，然后重写sql，根据dialect方言，添加对应的物理分页语句和物理分页参数。
