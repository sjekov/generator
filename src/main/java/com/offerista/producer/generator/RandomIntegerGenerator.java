package com.offerista.producer.generator;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/generate")
public class RandomIntegerGenerator {
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> findAll() {
        List<Integer> randomNumbers = getRandomNumbers();
        createCSVFile(randomNumbers);
        return Flux.fromIterable(randomNumbers)
                .zipWith(Flux.interval(Duration.ofMillis(200)), (item, time) -> item);
    }

    public List<Integer> getRandomNumbers() {
        Random random = new Random();
        List<Integer> ints = random.ints(100l, 0, 200)
                .boxed()
                .collect(Collectors.toList());
        return ints;
    }

    public void createCSVFile(List<Integer> numbersData) {
        try {
            FileWriter out = new FileWriter("data.csv", true);
            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.builder().build())) {
                for (Integer num : numbersData) {
                    printer.printRecord(num);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
