insert into werknemers(familienaam,
                       voornaam,
                       email,
                       chefid,
                       jobtitelid,
                       salaris,
                       geboorte)
values ('test',
        'test',
        'test',
        null,
        (select id from jobtitels where naam = 'test'),
        100,
        {d '2000-1-1'});