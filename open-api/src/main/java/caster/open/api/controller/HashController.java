package caster.open.api.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import saber.crypto.HashUtils;
import saber.util.JMap;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/hash")
public class HashController {

    @RequestMapping(value = "/md5", method = RequestMethod.GET)
    public Object md5(@RequestParam("data") String data, @RequestParam(name = "charset", defaultValue = "utf-8") String charset)
            throws NoSuchAlgorithmException {
        return JMap.on(200).setData(HashUtils.md5(data, charset));
    }

    @RequestMapping(value = "/sha1", method = RequestMethod.GET)
    public Object sha1(@RequestParam("data") String data, @RequestParam(name = "charset", defaultValue = "utf-8") String charset)
            throws NoSuchAlgorithmException {
        return JMap.on(200).setData(HashUtils.sha1(data, charset));
    }

    @RequestMapping(value = "/sha256", method = RequestMethod.GET)
    public Object sha256(@RequestParam("data") String data, @RequestParam(name = "charset", defaultValue = "utf-8") String charset)
            throws NoSuchAlgorithmException {
        return JMap.on(200).setData(HashUtils.sha256(data, charset));
    }

    @RequestMapping(value = "/sha384", method = RequestMethod.GET)
    public Object sha384(@RequestParam("data") String data, @RequestParam(name = "charset", defaultValue = "utf-8") String charset)
            throws NoSuchAlgorithmException {
        return JMap.on(200).setData(HashUtils.sha384(data, charset));
    }

    @RequestMapping(value = "/sha512", method = RequestMethod.GET)
    public Object sha512(@RequestParam("data") String data
            , @RequestParam(name = "charset", defaultValue = "utf-8") String charset
            , @RequestParam(name = "uppercase", required = false) String uppercase) throws NoSuchAlgorithmException {
        String sha512 = HashUtils.sha512(data, charset);
        return JMap.on(200).setData(StringUtils.isNotBlank(uppercase) ? sha512.toUpperCase() : sha512.toLowerCase());
    }

}
