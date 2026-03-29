package ru.app.work.service;

public class TestServiceImpl implements TestService{

    private final IOService ioService;

    public TestServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
    }
}
