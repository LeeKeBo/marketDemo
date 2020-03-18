##  一个模拟多并发的demo
* 使用spring boot + mybatis 简易搭建
* 通过 [网页](./testHtml/test.html) 模拟多次访问
* 模拟不加锁时会出现库存小于0的情况
* 解决方法
    * 加排它锁解决
    * 加乐观锁解决
    * 使用redis处理高并发(待加)
