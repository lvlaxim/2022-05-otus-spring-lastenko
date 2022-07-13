package ru.lastenko.studenttest.service;

import org.springframework.stereotype.Service;
import ru.lastenko.studenttest.dao.CheckDao;
import ru.lastenko.studenttest.model.Check;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CheckServiceImpl implements CheckService {

    private final CheckDao checkDao;

    @Override
    public List<Check> getAll() {
        return checkDao.getAll();
    }
}
