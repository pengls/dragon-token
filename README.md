# token create/parse

------

## 简介

- 支持生成规范的Jwt Token

- 支持生成更安全的加密Token

## demo
- 标准jwt token

```java
@Test
public void t2() throws InterruptedException {
    Stu stu = new Stu("Z-01202323","hh",11);
    String token = TokenFactory.getToken(TokenType.JWT_TOKEN).create(
    	TokenBuilder.builder()
    			.data(stu)
    			.algorithm(TokenAlgorithm.HmacSHA256)
   			 	.expire(3000)
    			.build()
    );


    System.out.println(token);
    //Thread.sleep(4000);

    Stu stut = TokenFactory.getToken(TokenType.JWT_TOKEN).parse(
    	TokenParser.builder()
    			.token(token)
    			.checkExpire(true)
    			.dataType(Stu.class)
    			.build()
    );

    System.out.println(stut.getUid() + "--" + stut.getName() + "--" + stut.getAge());
}
```
- 更安全的加密token

```java
@Test
public void tt1() throws InterruptedException {
    Stu stu = new Stu("Z-01202323","hh",11);
    String token = TokenFactory.getToken(TokenType.CRYPTO_TOKEN).create(
        TokenBuilder.builder()
        .data(stu)
        //指定压缩算法
        .compression(Compression.DEFLATE)
        //指定加密算法
        .algorithm(TokenAlgorithm.AES)
        //密钥
        .key("abc12123112313213456778843213431")
        //有效期
        .expire(3000)
        .build());


    System.out.println(token);
    //Thread.sleep(4000);

    Stu stut = TokenFactory.getToken(TokenType.CRYPTO_TOKEN).parse(
        TokenParser.builder()
        .algorithm(TokenAlgorithm.AES)
        .checkExpire(true)
        .key("abc12123112313213456778843213431")
        .compression(Compression.DEFLATE)
        .token(token)
        .dataType(Stu.class)
        .build());


    System.out.println(stut.getUid() + "--" + stut.getName() + "--" + stut.getAge());

}
```