package com.dragon.token;

import com.dragon.token.compression.Compression;
import com.dragon.token.serialize.SerializeType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;

/**
 * @ClassName: TokenBuilder
 * @Description: token builder
 * @Author: pengl
 * @Date: 2020/4/1 20:57
 * @Version V1.0
 */
@Slf4j
@Data
@Builder
public class TokenParser implements Serializable {
    /**
     * crypto algorithm
     */
    private TokenAlgorithm algorithm;
    /**
     * compression algorithm
     */
    private Compression compression;
    /**
     * Serialize type
     */
    @Builder.Default
    private SerializeType serializeType = SerializeType.KRYO;
    /**
     * the crypto key
     */
    private String key;
    /**
     * token
     */
    private String token;
    /**
     * checkExpire
     */
    private boolean checkExpire;

    private Class dataType;



    @Tolerate
    public TokenParser(){}

}
