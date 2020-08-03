package org.ganjp.api.core.web.response.success;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Success Response payload.
 *
 * <p>The payload sample:</p>
   <pre>
   {
     "timestamp": "2020-06-06T11:11:17.058+00:00",
     "status": 200,
     "message": "save success",
     "data": {
       "accountInfo": {..},
       "userName": "bob"
     }
   }
   </pre>
 *
 *
 * @author Jianping
 * @date 20/06/2020
 * @version 1.0.0
 *
 */
@Data
public class SuccessResponse implements Serializable {
    private Timestamp timestamp;
    private Integer status;
    private String message;
    private Object data;

    /**
     * Set status, message, timestamp and data to payload
     *
     * @param message eg: "save success"
     * @param data eg: accountInfo or "accountInfo", accountInfo
     */
    public SuccessResponse(String message, Object... data) {
        this.status = 200;
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());

        // set data
        if (data.length == 1) {
            this.data = data[0];
        } else if (data.length >= 2 && data.length % 2 == 0) {
            HashMap map = new HashMap();
            for (int index=0; index < data.length - 1; index=index+2 ) {
                map.put(data[index], data[index+1]);
            }
            this.data = map;
        }
    }
}
