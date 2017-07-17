## levyx-commonTemplete
    springmvc,redis,mongo,mybatis集成的通用项目

#### 注解部分
    @RestController
        @RestController是@Controller和@ResponseBody的结合体，两个标注合并起来的作用。
    @InitBinder(WebDataBinder binder)
        有些类型的数据是无法自动转换的，比如请求参数中包含时间类型的数据，无法自动映射到Controller里的Date参数。
        需要使用@initBinder注解为binder提供一个数据的转换器，这个转换器可以自己实现，也可以用spring官方的一些实现。
        诸如CustomDateEditor ，CustomBooleanEditor，CustomNumberEditor等许多
        @InitBinder用于在@Controller中，表示为当前控制器注册一个属性编辑器或者其他，只对当前的Controller有效。如果想要
        作用于所有controller，可以继承WebBindingInitializer这个类，自己实现initBinder方法
        经过测试，此注解只能对路径中的参数(@RequestParam)起作用，例如@RequestBody中的参数是不起作用的。



