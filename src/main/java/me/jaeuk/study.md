### 리플렉션 정리 및 활용

리플렉션 사용시 주의할 것 (잘못 사용하는 경우)
- 지나친 사용은 성능 이슈를 야기할 수 있다. 반드시 필요한 경우에만 사용할 것
- 컴파일 타임에 확인되지 않고 런타임 시에만 발생하는 문제를 만들 가능성이 있다.
- 접근 지시자를 무시할 수 있다.

스프링
- 의존성 주입
- MVC 뷰에서 넘어온 데이터를 객체에 바인딩 할 때

하이버네이트
- @Entity 클래스에 Setter가 없다면 리플렉션을 사용한다.

JUnit
- junit 프레임웍 사이에서 사용할 reflectionUtils 를 만들어서 사용하고 있다. 범용적으로 사용하는것은 x
- https://junit.org/junit5/docs/5.0.3/api/org/junit/platform/commons/util/ReflectionUtils.html

참고
- https://docs.oracle.com/javase/tutorial/reflect/index.html

