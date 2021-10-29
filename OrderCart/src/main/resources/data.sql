INSERT INTO public.categories (idcategory, description) VALUES (1, 'app');
INSERT INTO public.categories (idcategory, description) VALUES (2, 'Winter Wear');
INSERT INTO public.categories (idcategory, description) VALUES (3, 'Summer Wear');
INSERT INTO public.products (idproduct, description, price, stock, idcategory) VALUES (1, 'string', 100, 2, null);
INSERT INTO public.products (idproduct, description, price, stock, idcategory) VALUES (2, 'Round Tshirt', 199, 10, 2);
INSERT INTO public.products (idproduct, description, price, stock, idcategory) VALUES (3, 'Jacket', 1999, 10, 3);