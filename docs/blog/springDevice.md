# What is Spring Mobile Device

<b>Spring Mobile Device </b> is a library that provides a simple way to detect the <b>device type of the user</d>. It is based on the user-agent string of the request. It is a part of the <b> Spring Mobile project.</b>

## Core Components of Spring Mobile

### Automatic Device Detection

<b>Spring Mobile Device provides automatic device detection</b>. It detects the device type of the user based on the user-agent string of the request. It provides a <b>DeviceResolver interface </b> that can be used to customize the device detection.

## code example

```java
public class DeviceResolverHandlerInterceptor implements HandlerInterceptor {
    private DeviceResolver deviceResolver;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Device device = deviceResolver.resolveDevice(request);
        request.setAttribute(DEVICE_ATTRIBUTE, device);
        return true;
    }
}
```

### Site Preference Management:

Spring Mobile Device provides a SitePreferenceResolver interface that can be used to customize the site preference management.

#### code SITE_PREFERENCE_ATTRIBUTE

```java
public class SitePreferenceHandlerInterceptor implements HandlerInterceptor {
    private SitePreferenceResolver sitePreferenceResolver;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SitePreference sitePreference = sitePreferenceResolver.resolveSitePreference(request);
        request.setAttribute(SITE_PREFERENCE_ATTRIBUTE, sitePreference);
        return true;
    }
}
```

### Site Switching
Spring Mobile Device provides a SiteSwitcher interface that can be used to customize the site switching.

### code example

```java
public class SiteSwitcherHandlerInterceptor implements HandlerInterceptor {
    private SiteSwitcher siteSwitcher;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SitePreference sitePreference = siteSwitcher.switchSite(request);
        request.setAttribute(SITE_PREFERENCE_ATTRIBUTE, sitePreference);
        return true;
    }
}
```

### Device Aware View Manager

Spring Mobile Device provides a DeviceAwareViewManager interface that can be used to customize the device aware view management.

### code example

```java
public class DeviceAwareViewManagerImpl implements DeviceAwareViewManager {
    private Map<Device, String> mobilePrefixes = new HashMap<Device, String>();
    private String defaultPrefix;
    public String getViewName(String viewName, Device device) {
        String prefix = mobilePrefixes.get(device);
        if (prefix == null) {
            prefix = defaultPrefix;
        }
        return prefix + viewName;
    }
}
```
 

## How to use it

### Maven Dependency

```xml
<dependency>
    <groupId>org.springframework.mobile</groupId>
    <artifactId>spring-mobile-device</artifactId>
    <version>1.1.5.RELEASE</version>
</dependency>

```
## Repository Exist
From personal testing, I found out the device instance can be null. So to make things easier I would prefer the current dependency used not to change 
because it works well with the current version of spring boot except for stable releases.->..[maven-snapshot-release-repository](https://www.baeldung.com/maven-snapshot-release-repository)
```xml
<repository>
    <id>spring-milestones</id>
    <name>Spring Milestones</name>
    <url>https://repo.spring.io/libs-milestone</url>
    <snapshots>
        <enabled>false</enabled>
    </snapshots>
    <releases>
        <enabled>true</enabled>
    </releases>
</repository>
```


### Configuration

```java
@Configuration
@EnableWebMvc
@EnableSpringConfigured
@ComponentScan(basePackages = "com.fas")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
        return new DeviceResolverHandlerInterceptor();
    }

    @Bean
    public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
        return new DeviceHandlerMethodArgumentResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(deviceResolverHandlerInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(deviceHandlerMethodArgumentResolver());
    }
}
```

### Device Resolver Fallback resolution

This is the default implementation of the DeviceResolver interface. It uses the user-agent string of the request to detect the device type of the user. It also provides a fallback resolution mechanism that can be used to customize the device detection.
```java
public interface DeviceResolver {
    Device resolveDevice(HttpServletRequest request);
}
``` 
The device interface returns a device types.
```java
public interface Device {
    boolean isNormal();
    boolean isMobile();
    boolean isTablet();
}
```

### Controller

```java
@Controller
public class DeviceController {

    @RequestMapping("/device")
    public String device(@RequestParam(value = "name", required = false, defaultValue = "Fas") String name, Model model, Device device) {
        model.addAttribute("name", name);
        model.addAttribute("device", device);
        return "device";
    }
}
```

### View

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Device</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1 th:text="'User, ' + ${name} + '!'" />
<h2 th:text="'Device: ' + ${device}" />
</body>
</html>
```
 

### Result
<h5>User, Fas!</h5>
<h5>Device: MOBILE | TABLET | NORMAL  </h5>

## Conclusion

<b>Spring Mobile Device provides automatic device detection</b>, <b>site preference management, site switching, and device aware view management</b>. It also provides a <b>DeviceResolver interface</b> that can be used to customize the device detection. It also provides a <b>SitePreferenceResolver interface</b> that can be used to customize the site preference management. It also provides a <b>SiteSwitcher interface</b> that can be used to customize the site switching. It also provides a <b>DeviceAwareViewManager interface</b> that can be used to customize the device aware view management.
Notice that there is alternatives to the device resolution or getting properties of device. refer to the link below for more information. [Alternative to Device Resolution](https://ykshinde.wordpress.com/2014/12/03/springs-device-detection-spring-boot-vs-wurfl-device-detection/)
 


## Reference

* [Spring docs](https://docs.spring.io/spring-mobile/docs/1.1.0.M2/reference/html/spring-mobile-device.html)
* [Baeldung](https://www.baeldung.com/spring-mobile-device)
* [Baeldung](https://www.baeldung.com/maven-snapshot-release-repository)