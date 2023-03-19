package ru.netology.moneytransferapplication.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.moneytransferapplication.model.Amount;
import ru.netology.moneytransferapplication.model.Operation;

import java.util.stream.Stream;

public class TransferRepositoryImplTest {
    TransferRepositoryImpl transferRepository;
    @BeforeEach
    public void initRepository(){
        transferRepository = new TransferRepositoryImpl();
    }

    @ParameterizedTest
    @MethodSource("saveArguments")
    @DisplayName("Проверка метода save репозитория, сравниваются id операции, при первом сохранении id равен 0")
    public void repositorySaveTest(Operation operation){
        String result = transferRepository.save(operation);
        String expected = "0";
        Assertions.assertEquals(expected,result);
    }
    @ParameterizedTest
    @MethodSource("saveSecondArguments")
    @DisplayName("Проверка метода save репозитория, предварительно вносятся две записи в репозиторий, сравниваются id операции, при третьем сохранении id равен 2")
    public void repositorySaveSecondTest(Operation operation){
        transferRepository.save(new Operation("1234123412341234", "1223", "123","12312312312312312", new Amount(100, "rub")));
        transferRepository.save(new Operation("1234123412341234", "1223", "123","12312312312312312", new Amount(100, "rub")));
        String result = transferRepository.save(operation);
        String expected = "2";
        Assertions.assertEquals(expected,result);
    }
    private static Stream<Arguments> saveArguments(){
        return Stream.of(
                Arguments.of(new Operation("1234123412341234", "1223", "123","12312312312312312", new Amount(100, "rub"))),
                Arguments.of(new Operation("4234543253252342", "3242", "143","12312312312312312", new Amount(1000, "dol"))),
                Arguments.of(new Operation("1235678679597655", "9765", "564","12312312312312312", new Amount(10000, "rub")))
        );
    }
    private static Stream<Arguments> saveSecondArguments(){
        return Stream.of(
                Arguments.of(new Operation("1234123412341234", "1223", "123","12312312312312312", new Amount(100, "rub"))),
                Arguments.of(new Operation("4234543253252342", "3242", "143","12312312312312312", new Amount(1000, "dol"))),
                Arguments.of(new Operation("1235678679597655", "9765", "564","12312312312312312", new Amount(10000, "rub")))
        );
    }
}
