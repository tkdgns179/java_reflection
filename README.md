### Java Reflection은 무엇인가?
- <span style="color: #C3ACD0">런타임</span> 도중에 객체의 정보 에 접근 가능한 언어이자 JVM의 기능
- Reflection API를 통하여도 이 기능을 사용할 수 있음 <span style="color: #F5EA5A">Reflection API</span>는  
    JDK에 달린 클래스 집합과 메소드임
- Reflection API를 이용하여 유연한 코드를 작성가능
  - 다른 소프트웨어 컴포넌트를 런타임에 링킹
  - 다른 코드 수정없이 <span style="color: #FC7300">새로운 프로그램 플로우</span>를 만듦
- Reflection은 실행하고있는 객체의 타입과 클래스에 의존하는 행동을 변화하고 적응시키는 다목적 알고리즘을 작성함

리플랙션사진

### More on Reflection
- 프로그램을 실행하면서 앱 객체와 클래스를 입력값으로 사용할 수 있음
- 강력한 라이브러리, 프레임워크, 소프트웨어 디자인들을 만들 수 있음
- Reflection이 없다면 불가능한 일들

### 자바 리플렉션을 사용하는 유닛 테스트 표준 프레임 워크
####1. JUnit
```java

public class CarTest {
    public void setUp() {..}
    public void testDrive() {..}
    public void testBrake() {..}
    ...
    
    // Junit이 없으면 메인메소드를 작성하여 실행해야함
    public static void main() {
        CarTest carTest = new CarTest();
        carTest.setUp();
        carTest.testDrive();
        carTest.setUp();
        carTest.testBrake();
    }
}
```

```java
public class CarTest {
    // 프로그램 구동
    // CarTest 인스턴스 생성
    // setup, test 메소드 서치
    // 테스트들 구동
    // 테스트 report
    @Before
    public void setUp() {..}
    
    @Test
    public void testDrive() {..}
    
    @Test
    public void testBrake() {..}
    
}

```

####2.  Dependency Injection (의존성 주입), 구성 프레임워크
- Spring / Spring Boot / Google Guice etc..

```java
public class Car {
    ...
    public Car() {
        // 아마 그들 자신의 의존성을 가짐
        this.engine = new Engine(); 
        this.driver = createDriver();
    }
    
    ...
    public void drive() {..}
}
```
2. Spring
```java
public class Car {
    ...
    
    @Autowired
    public Car(Engine engine, Driver driver) {
        this.engine = engine; // <- @Bean으로 등록한 Engine과 Driver를 찾고 객체로 만듦
        this.driver = driver;
    }
    
    ...
    public void drive() {..}
}
```

```java
import javax.management.MXBean;

@Configuration
public class Config {

    @Bean
    public Engine createEngine() {..}
    
    @Bean
    public Driver createDrive() {..}
}
```
####3. JSON Serialization / Deserialization (JSON 직렬화 / 역직렬화)
- JSON
- Gson
 
```javascript
{
  name : "John",
  age : 25,
  pet : [
      'dog',
      'cat'
  ]
}
```
```java
public class Person {
    private int age;
    private String name;
    private List<String> pets;
    
    //Getters
    //...
    
    //Setters
    //...
}
```

```java
// Jackson Serialization
String json = "{\"name\" : \"John\", \"age\" : 25, \"pets\" : [ \"dog\", \"cat\"]}";

Person person = objectMapper.readValue(json, Person.class)
```

```java
// Jackson Parsing
Person person = new Person("John", 25, ["dog", "cat"]);

String json objectMapper.writeValueAsString(person);
```

### More Reflection Use Cases
- 로깅 프레임 워크
- ORM(JPA Hibernate.. ) tools 
- Web 프레임워크
- 개발 툴
- 기타 등등..
- 앱과 라이브러리나 고유한 기능을 더하기 위해서도 사용

### 자바 리플렉션의 문제점 
- 자바 리플렉션의 역할이 매우 중요함
- 보이지 않는 구조에 접근할 수 있어서 어려움이 발생
- 부적절하게 사용되거나, 목적에 부합하지 않는 방법으로 사용하게된다면 겪게될 리스크
  - 유지보수가 매우 어려워짐
  - 실행이 느림
  - 실행하기 위험함
- 큰 힘에는 큰 책임이 따른다

### Roadmap for Overcoming
- 전체적인 코스에서 어떻게 리플렉션을 사용하는지 배운다
  - 정확하게
  - 안전하게
- 실무에서 사용되는 유즈케이스를 통해 우리의 지식을 학습한다
- 코스의 후반부에 접어들면
  - 우리의 기술에 자신감을 가진다
  - 언제 리플렉션을 사용해야할지 직관이 생긴다

### Summary
- 자바 리플렉션은 언어이자 JVM의 기능이다
- 자바코드를 작성하고 다른 코드를 분석함 우리의 어플리케이션의 클래스 객체에 접근
- 여러가지 사용 례
  - 인기있는 외부라이브러리, 프레임워크
  - 우리의 어플리케이션
- 주의할점이 있는 자바 리플렉션의 문제

### 리플랙션 엔트리 포인트

- Class<?>는 우리가 작성한 코드를 리플렉트하는 진입점임
- Class<?> 타입 객체엔 정보가 담겨있음
  - 객체의 런타임 타입
  - 우리의 어플리케이션 속 클래스
- 정보가 담고있는 것
  - 어떤 메소드와 필드를 가지고 있는지
  - 어떤 클래스를 확장하는지
  - 어떤 인터페이스를 구현하는지

### Class<?> 객체를 얻는 3가지 방법

####1. Object.getClass()
```java
String stringObject = "some-string";

Car car = new Car();

Map<String, Integer> map = new Hashmap<>;

Class<String> stringClass = stringObject.getClass();

Class<Car> carClass = car.getClass();

Class<?> mapClass = map.getClass(); // return HashMap class 맵 인터페이스를 리턴하지 않음
```

```java
boolean condition = true;

int value = 55;

double price = 1.5;

Class booleanClass = condition.getClass(); // compliation error

Class intClass = value.getClass(); // compliation error

Class doublePrice = price.getClass(); // compliation error
```

####2. ".class" suffix to a type name
```java
Class<String> stringClass = String.class
...

Class booleanType = boolean.class;
```

####3. Class.forName(...) 
```java
Class<?> stringType = Class.forName("java.lang.String"); 

Class<?> carType = Class.forName("vehicles.Car");

Class<?> engineType = Class.forName("vehicles.Car$Engine") // 이너 클래스 접근
```

```java
package vehicles
class Car {
    ...
    static class Engine {
        ...
    }
}
```

```java
boolean condition = true;

int value = 55;

double price = 1.5;

Class booleanClass = Class.forName("boolean"); // Runtime Error...
...
```

### Notes on Class.forName()

- 클래스 이름을 잘못 입력할 가능성이 크고 런타임 에러인 ClassNotFoundException를 던짐 
- Class.forName의 인수로 전달한 클래스가 존재하지 않을 경우
- Class.forName(..)의 메소드가 Class<?> 객체를 얻기엔 가장 덜 안전함
- Class<?> 객체를 얻기위해 Class.forName()메소드를 사용해야하는 유즈케이스가 있음
```xml
<!--사용자 정의 구성 파일에서 전달될 때 이 메서드( Class.forName() )를 사용함-->
<appender name="fileAppender"
  class="org.apche.log4j.RollingFileAppender">
  <layout class="org.apache.log4j.PatternLayout" />
</appender>

<!--
Class<?> vehicle = Class.forName(value)
-->
<bean id="vehicle" class="vehicles.Motocycle">
  <property name="numberOfWheels" value="2" />  
</bean>
```
![사진]
### Generics - Java Wildcards
![제네릭]

```java
Class<?> carClass = Class.forName("vehicles.Car")

Map<String, Integer> genericMap = new HashMap<>();

Class<?> hashMapClass = genegicMap.getClass();
```

```java
// Restricts to only type that extend Collection
public List<String> findAllMethods(Class<? extends Collection> clazz) {
    
}
```

### Summary
- Class<?> - 리플렉션을 사용하는 진입점
- 클래스 오브젝트를 얻을 수 있는 세 가지 방법 
  - getClass(), dot operation ".class", Class.forName()
- Java Wildcards