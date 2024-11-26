package tn.esprit.tpfoyer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlocServiceImplTest {
    @Mock
    BlocRepository blocRepository;
    @InjectMocks
    BlocServiceImpl blocServiceimpl;

    @Test
    @Order(1)
    public void addBloc() {
        Bloc bloc = Bloc.builder().idBloc(1L).nomBloc("aaa").capaciteBloc(30).build();

        Mockito.when(blocRepository.save(Mockito.any(Bloc.class))).thenReturn(bloc);

        Bloc blocAdded = blocServiceimpl.addBloc(bloc);

        Assertions.assertNotNull(blocAdded);
        log.info("Bloc added with ID: " + blocAdded.getIdBloc());

        Mockito.verify(blocRepository).save(Mockito.any(Bloc.class));
    }

    @Test
    @Order(2)
    public void updateBloc() {
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("okok");

        Mockito.when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc result = blocServiceimpl.modifyBloc(bloc);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(bloc.getNomBloc(), result.getNomBloc());

        Mockito.verify(blocRepository).save(Mockito.any(Bloc.class));
        log.info("le nom du bloc est :" + result.getNomBloc());
    }

    @Test
    @Order(3)
    public void retrieveBloc() {

        Bloc bloc = Bloc.builder().idBloc(1L).nomBloc("aaa").capaciteBloc(30).build();
        Mockito.when(blocRepository.findById(1L)).thenReturn(Optional.ofNullable(bloc));

        Bloc bloc1 = blocServiceimpl.retrieveBloc(1L);
        Assertions.assertNotNull(bloc1);

        Assertions.assertEquals(bloc.getIdBloc(), bloc1.getIdBloc());

        log.info("le nom du bloc est :" + bloc1.getNomBloc());

        Mockito.verify(blocRepository).findById(1L);


    }

    @Test
    @Order(4)
    public void deleteBlocById() {
        Bloc bloc = Bloc.builder().idBloc(1L).nomBloc("aaa").capaciteBloc(30).build();

        blocServiceimpl.removeBloc(bloc.getIdBloc());

        Mockito.verify(blocRepository).deleteById(bloc.getIdBloc());


    }

    @Test
    @Order(5)
    public void retrieveAllBlocs() {
        List<Bloc> blocs = new ArrayList<>();

        Bloc bloc1 = Bloc.builder().idBloc(1L).nomBloc("ccc").capaciteBloc(30).build();
        Bloc bloc2 = Bloc.builder().idBloc(2L).nomBloc("bbb").capaciteBloc(50).build();

        blocs.add(bloc1);
        blocs.add(bloc2);

        Mockito.when(blocRepository.findAll()).thenReturn(blocs);

        List<Bloc> blocsAdded = blocServiceimpl.retrieveAllBlocs();

        Assertions.assertNotNull(bloc1);
        Assertions.assertNotNull(bloc2);

        Assertions.assertNotNull(blocsAdded, "liste non vide");
        Assertions.assertEquals(2, blocsAdded.size(), "taille de la liste");
        Assertions.assertEquals(bloc1, blocsAdded.get(0));
        Assertions.assertEquals(bloc2, blocsAdded.get(1));

        Mockito.verify(blocRepository, Mockito.times(1)).findAll();

        log.info("Id 1 bloc :" + bloc1.getIdBloc());
        log.info("Id du bloc 1 ajouté a la liste :" + blocsAdded.get(0).getIdBloc());
        log.info("Id 2 bloc :" + bloc2.getIdBloc());
        log.info("Id du bloc 2 ajouté a la liste :" + blocsAdded.get(1).getIdBloc());
    }
}