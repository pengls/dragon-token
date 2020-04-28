package com.dragon.token;

import com.dragon.token.compression.Compression;
import com.dragon.token.serialize.SerializeType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.util.Date;

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
public class TokenBuilder implements Serializable {
    /**
     * crypto algorithm
     */
    @Builder.Default
    private CryptoTokenAlgorithm algorithm = CryptoTokenAlgorithm.AES;
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
     * create date
     */
    @Builder.Default
    private Date createDate = new Date();
    /**
     * expire date
     */
    private Date expireDate;
    /**
     * expire offset (ms)
     */
    private long expire;
    /**
     * biz data
     */
    private Object data;

    @Tolerate
    public TokenBuilder(){}

}
