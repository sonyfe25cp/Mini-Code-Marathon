package com.zada.hackathon.engine.service;

import com.zada.hackathon.gen.DataService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AIndexSearcher<T> implements DataService.Iface {
    static Logger logger = LoggerFactory.getLogger(AIndexSearcher.class);

    @Option(name = "-port", usage = "the port")
    protected int port = 5678;

    @Option(name = "-help", usage = "show the help")
    protected boolean help = false;


    private void bindAndListen() throws TTransportException {
        if (this.port < 0) {
            return;
        }
        DataService.Processor<AIndexSearcher<T>> processor = new DataService.Processor<>(this);
        TNonblockingServerSocket socket = new TNonblockingServerSocket(this.port);
        TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(socket)
                .processor(processor).protocolFactory(new TBinaryProtocol.Factory());

        args.selectorThreads(1);

        TThreadedSelectorServer server = new TThreadedSelectorServer(args);
        logger.info("{} listen on port: {}", this.getClass().getSimpleName(), this.port);
        server.serve();
    }

    //用于被override
    //在所有事情最前面执行
    public void prepare() {

    }

    public void parseArgsAndRun(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(120);

        try {
            parser.parseArgument(args);
            if (this.help) {
                System.err.println("java {{cp}} " + this.getClass().getCanonicalName() + " [options...] arguments...");
                parser.printUsage(System.err);
                System.exit(1);
            } else {
                prepare();
                after();
                this.bindAndListen();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected void after() {

    }

}
