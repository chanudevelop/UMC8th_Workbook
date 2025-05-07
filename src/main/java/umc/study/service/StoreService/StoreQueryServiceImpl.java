package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Store;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.StoreRepository.StoreRepositoryCustom;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {
    private final StoreRepository storeRepository;
    private final StoreRepositoryCustom storeRepositoryCustom;

    @Override
    public Optional<Store> findStore(Long id) {
        return  storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        {
            List<Store> filteredStores = storeRepositoryCustom.dynamicQueryWithBooleanBuilder(name, score);
            filteredStores.forEach(store -> System.out.println("Store: " + store));

            return filteredStores;
        }
    }
}
