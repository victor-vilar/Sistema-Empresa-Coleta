--RESIDUES
INSERT INTO residues(type,description)VALUES('EXTRAORDINÁRIO','CLASSE IIA');
INSERT INTO residues(type,description)VALUES('ENTULHO','CLASSE IIB');

--EQUIPMENTS
INSERT INTO equipments(equipment,size_cubic)VALUES('CONTAINER 1.2M³','1.2');
INSERT INTO equipments(equipment,size_cubic)VALUES('CONTAINER 240L','0.24');

--VEHICLES
INSERT INTO vehicles(type,plate,renavam,valid_crlv_url)VALUES('CAMINHAO_COMPACTADOR','XXX-0000','00000','/xxx0000-2023.jpg');
INSERT INTO vehicles(type,plate,renavam,valid_crlv_url)VALUES('CAMINHAO_POLIGUINDASTE','XXX-1111','1111','/xxx1111-2023.jpg');

--APPLICATION USERS
INSERT INTO application_users(username,password,profile_photo_url)VALUES('madruga','mama','/assets/madruga-profile.jpg');
INSERT INTO application_users(username,password,profile_photo_url)VALUES('chaves','cha','/assets/chaves-profile.jpg');

--ROLES
INSERT INTO roles(role_name)VALUES('ADMIN');
INSERT INTO roles(role_name)VALUES('CUSTOMER');
INSERT INTO roles(role_name)VALUES('MANAGER');

--APLICATION USERS ROLES
INSERT INTO users_roles(application_user_id,role_id)VALUES(1,1);
INSERT INTO users_roles(application_user_id,role_id)VALUES(1,3);
INSERT INTO users_roles(application_user_id,role_id)VALUES(2,2);

--CUSTOMERS
INSERT INTO clients(cpf_cnpj,name_company_name,is_active)VALUES('08454836000178','EMPRESA 1',true);
INSERT INTO clients(cpf_cnpj,name_company_name,is_active)VALUES('29439167000135','INSTITUTO MILITAR DE ESCOTEIROS DE APARTAMENTO',true);
INSERT INTO clients(cpf_cnpj,name_company_name,is_active)VALUES('43715989000122','COMANDO DOS PODERES FRACOS DE OPERAÇÕES ESPECIALIZADAS EM ABSTRAÇÕES BASTANTE ESPECIFICAS',true);

--SUPERVISORS
INSERT INTO supervisors(name,role,phone_number,email,customer_id)VALUES('ALFREDINHO ROBERTINHO','GERENTINHO','21999999999','gerentinho@gmail.com','08454836000178');
INSERT INTO supervisors(name,role,phone_number,email,customer_id)VALUES('AFRANIO NOVAES MENDONÇA CARDOSO VASCONCELOS','ESCOTEIRO','21999999999','escoteiro@gmail.com','29439167000135');

--CONTRACTS
INSERT INTO contracts(number,begin_date,end_date,customer_id,status)VALUES('1000','2022-12-31','2023-12-31','08454836000178',1);
INSERT INTO itens_contract(residue_id,equipment_id,equipment_quantity,max_qtd_year,item_value,description,measurement_unit,contract_id)
VALUES(1,1,100,1000,200,'only sometimes',1,1);

INSERT INTO contracts(number,begin_date,end_date,customer_id,status)VALUES('2000','2022-12-31','2023-12-31','29439167000135',1);
INSERT INTO itens_contract(residue_id,equipment_id,equipment_quantity,max_qtd_year,item_value,description,measurement_unit,contract_id)
VALUES(1,1,100,1000,200,'only sometimes',1,2);


--ADDRESSES
INSERT INTO address(address_name,address_number,complement,zip_code,city,state,requires_collection,customer_id)
VALUES('ENDERECO1','NUMERO1','COMPLEMENTO1','1111-111','CIDADE1','ESTADO1',true,'08454836000178');
INSERT INTO address(address_name,address_number,complement,zip_code,city,state,requires_collection,customer_id)
VALUES('ENDERECO2','NUMERO2','COMPLEMENTO2','2222-222','CIDADE2','ESTADO2',true,'29439167000135');
INSERT INTO address(address_name,address_number,complement,zip_code,city,state,requires_collection,customer_id)
VALUES('ENDERECO3','NUMERO3','COMPLEMENTO3','3333-333','CIDADE3','ESTADO3',true,'43715989000122');
