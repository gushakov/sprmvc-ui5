sprmvc-ui5
==========

The purpose of this demo application is to showcase the one possible set up for using [OpenUI5](http://sap.github.io/openui5/),
formerly known as SAPUI5, with [Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html)
framework.

It's a standard [Maven](http://maven.apache.org/) application which could be tested with embedded Tomcat server using
the following command.

`> mvn tomcat7:run`

OpenUI5 Web JAR
---------------

OpenUI5 runtime JavaScript libraries are available from [Download](http://sap.github.io/openui5/download.html) (under Apache 2.0 license).
I packaged them in a web JAR in `/WEB-INF/lib/openui5-runtime-1.16.8.jar`, which is used by Spring when configuring the
web application. See `com.github.springui5.conf.WebAppConfigurer` class.

Useful links
------------

Here are some useful links (some of the code is based on the content found there).

* [OpenUI5](http://sap.github.io/openui5/)
* [Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html)
* [OpenUI5 Demo Kit](https://openui5.hana.ondemand.com/#content/Overview.html)
* [Generating JSON Error Object Responses With Spring Web MVC](http://springinpractice.com/2013/10/09/generating-json-error-object-responses-with-spring-web-mvc)



