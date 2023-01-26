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