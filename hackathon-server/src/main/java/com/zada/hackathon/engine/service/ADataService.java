package com.zada.hackathon.engine.service;

import com.zada.hackathon.gen.DataService;
import com.zada.hackathon.gen.KeywordRequest;
import com.zada.hackathon.gen.KeywordResponse;
import org.apache.thrift.TException;

public class ADataService extends AIndexSearcher implements DataService.Iface {

    @Override
    public KeywordResponse searchKeyword(KeywordRequest req) throws TException {
        throw new TException();
    }
}
