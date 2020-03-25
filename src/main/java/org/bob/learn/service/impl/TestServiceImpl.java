package org.bob.learn.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bob.learn.service.TestService;
import org.bob.learn.web.model.Result;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.LongAdder;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    private static volatile LongAdder counter = new LongAdder();

    @Override
    public Result get() {
        Result result = new Result();
        result.setCode("000000");
        result.setMsg("success");
        counter.increment();
        log.info(String.valueOf(counter.longValue()));
        return result;
    }
}
