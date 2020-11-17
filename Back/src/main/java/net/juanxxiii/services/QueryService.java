package net.juanxxiii.services;

import net.juanxxiii.db.entity.*;
import net.juanxxiii.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class QueryService {

    private final ClientRepository clientRepository;
    private final SupplierRepository supplierRepository;
    private final StaffRepository staffRepository;
    private final ClientTelephoneRepository clientTelephoneRepository;
    private final ClientDirectionRepository clientDirectionRepository;
    private final PositionStaffRepository positionStaffRepository;
    private final SaleRepository saleRepository;
    private final SaleLineRepository saleLineRepository;
    private final ProductRepository productRepository;
    private final ReceiptRepository receiptRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseLineRepository purchaseLineRepository;
    private final SupplierTelephoneRepository supplierTelephoneRepository;
    private final SupplierDirectionRepository supplierDirectionRepository;
    private final PurchaseRepository purchaseRepository;


    @Autowired
    public QueryService(ClientRepository clientRepository,
                        SupplierRepository supplierRepository,
                        StaffRepository staffRepository,
                        ClientTelephoneRepository clientTelephoneRepository,
                        ClientDirectionRepository clientDirectionRepository,
                        PositionStaffRepository positionStaffRepository,
                        SaleRepository saleRepository,
                        SaleLineRepository saleLineRepository,
                        ProductRepository productRepository,
                        ReceiptRepository receiptRepository,
                        SupplierTelephoneRepository supplierTelephoneRepository,
                        SupplierDirectionRepository supplierDirectionRepository,
                        PurchaseRepository purchaseRepository,
                        ReceiptRepository receiptRepository,
                        PurchaseRepository purchaseRepository,
                        PurchaseLineRepository purchaseLineRepository) {
        this.clientRepository = clientRepository;
        this.supplierRepository = supplierRepository;
        this.staffRepository = staffRepository;
        this.clientTelephoneRepository = clientTelephoneRepository;
        this.clientDirectionRepository = clientDirectionRepository;
        this.positionStaffRepository = positionStaffRepository;
        this.saleRepository = saleRepository;
        this.saleLineRepository = saleLineRepository;
        this.productRepository = productRepository;
        this.receiptRepository = receiptRepository;
        this.supplierTelephoneRepository = supplierTelephoneRepository;
        this.supplierDirectionRepository = supplierDirectionRepository;
        this.purchaseRepository = purchaseRepository;
        this.purchaseRepository = purchaseRepository;
        this.purchaseLineRepository = purchaseLineRepository;
    }

    //Client queryList
    public Client saveClient(Client newClient) {
        List<ClientTelephone> telephones = null;
        List<ClientDirection> directions = null;
        List<Sale> sales = null;
        if (!newClient.getTelephones().isEmpty()) {
            telephones = newClient.getTelephones();
            newClient.setTelephones(null);
        }
        if (!newClient.getDirections().isEmpty()) {
            directions = newClient.getDirections();
            newClient.setDirections(null);
        }
        if (!newClient.getSales().isEmpty()) {
            sales = newClient.getSales();
            newClient.setSales(null);
        }
        clientRepository.save(newClient);
        int id = clientRepository.lastId();
        if (telephones != null) {
            telephones.forEach(telephone -> {
                telephone.setClient(id);
                clientTelephoneRepository.save(telephone);
            });
        }
        if (directions != null) {
            directions.forEach(direction -> {
                direction.setClient(id);
                clientDirectionRepository.save(direction);
            });
        }
        if (sales != null) {
            sales.forEach(sale -> {
                sale.setClient(id);
                saveSale(sale);
            });
        }
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> getClientList() {
        return clientRepository
                .findAll();
    }

    public Client getClient(int id) {
        return clientRepository
                .findById(id)
                .orElse(null);
    }

    public int updateClient(Client newClient, int id) {
        return clientRepository.findById(id)
                .map(client -> {
                    List<ClientTelephone> telephones = client.getTelephones();
                    List<ClientDirection> directions = client.getDirections();
                    List<Sale> sales = client.getSales();
                    newClient.getTelephones()
                            .forEach(telephone -> {
                                if (!telephones.contains(telephone)) {
                                    telephone.setClient(client.getId());
                                    clientTelephoneRepository.save(telephone);
                                }
                            });
                    telephones.forEach(telephone -> {
                        if (!newClient.getTelephones().contains(telephone)) {
                            clientTelephoneRepository.deleteById(telephone.getId());
                        }
                    });
                    newClient.getDirections()
                            .forEach(direction -> {
                                if (!directions.contains(direction)) {
                                    direction.setClient(client.getId());
                                    clientDirectionRepository.save(direction);
                                }
                            });
                    directions.forEach(direction -> {
                        if (!newClient.getDirections().contains(direction)) {
                            clientDirectionRepository.deleteById(direction.getId());
                        }
                    });
                    newClient.getSales()
                            .forEach(sale -> {
                                if (!sales.contains(sale)) {
                                    sale.setClient(client.getId());
                                    saveSale(sale);
                                }
                            });
                    sales.forEach(sale -> {
                        if (!newClient.getSales().contains(sale)) {
                            saleRepository.deleteById(sale.getId());
                        }
                    });
                    return clientRepository.updateClient(newClient.getFullName(), newClient.getDni(), newClient.getEmail(), newClient.getIban(), id);
                })
                .orElse(-1);
    }

    public int partialUpdateClient(Client newClient, int id) {
        return clientRepository.findById(id)
                .map(client -> {
                    if (newClient.getFullName() != null) {
                        clientRepository.updateClientName(newClient.getFullName(), id);
                    }
                    if (newClient.getDni() != null) {
                        clientRepository.updateClientDni(newClient.getDni(), id);
                    }
                    if (newClient.getIban() != null) {
                        clientRepository.updateClientIban(newClient.getIban(), id);
                    }
                    if (newClient.getEmail() != null) {
                        clientRepository.updateClientEmail(newClient.getEmail(), id);
                    }
                    if (newClient.getTelephones() != null) {
                        newClient.getTelephones().forEach(clientTelephone -> {
                            if (!client.getTelephones().contains(clientTelephone)) {
                                clientTelephone.setClient(client.getId());
                                clientTelephoneRepository.save(clientTelephone);
                            }
                        });
                        client.getTelephones().forEach(telephone -> {
                            if (!newClient.getTelephones().contains(telephone)) {
                                clientTelephoneRepository.deleteById(telephone.getId());
                            }
                        });
                    }
                    if (newClient.getDirections() != null) {
                        newClient.getDirections().forEach(clientDirection -> {
                            if (!client.getDirections().contains(clientDirection)) {
                                clientDirection.setClient(client.getId());
                                clientDirectionRepository.save(clientDirection);
                            }
                        });
                        client.getDirections().forEach(direction -> {
                            if (!newClient.getDirections().contains(direction)) {
                                clientDirectionRepository.deleteById(direction.getId());
                            }
                        });
                    }
                    if (newClient.getSales() != null) {
                        newClient.getSales()
                                .forEach(sale -> {
                                    if (!client.getSales().contains(sale)) {
                                        sale.setClient(client.getId());
                                        saveSale(sale);
                                    }
                                });
                        client.getSales().forEach(sale -> {
                            if (!newClient.getSales().contains(sale)) {
                                saleRepository.deleteById(sale.getId());
                            }
                        });
                    }
                    return 1;
                })
                .orElse(-1);
    }

    public void deleteClient(int id) {
        clientRepository
                .delete(Objects
                        .requireNonNull(clientRepository
                                .findById(id)
                                .orElse(null)));
    }

    //Supplier queryList
    public Supplier saveSupplier(Supplier newSupplier) {
        List<SupplierTelephone> telephones = null;
        List<SupplierDirection> directions = null;
        List<Purchase> purchases = null;
        if (!newSupplier.getTelephones().isEmpty()) {
            telephones = newSupplier.getTelephones();
            newSupplier.setTelephones(null);
        }
        if (!newSupplier.getDirections().isEmpty()) {
            directions = newSupplier.getDirections();
            newSupplier.setDirections(null);
        }
        if (!newSupplier.getPurchases().isEmpty()) {
            purchases = newSupplier.getPurchases();
            newSupplier.setPurchases(null);
        }
        supplierRepository.save(newSupplier);
        int id = supplierRepository.lastId();
        if (telephones != null) {
            telephones.forEach(telephone -> {
                telephone.setSupplier(id);
                supplierTelephoneRepository.save(telephone);
            });
        }
        if (directions != null) {
            directions.forEach(direction -> {
                direction.setSupplier(id);
                supplierDirectionRepository.save(direction);
            });
        }
        if (purchases != null) {
            purchases.forEach(purchase -> {
                purchase.setSupplier(id);
                purchaseRepository.save(purchase);
            });
        }
        return supplierRepository.findById(id).orElse(null);
    }

    public List<Supplier> getSupplierList() {
        return supplierRepository
                .findAll();
    }

    public Supplier getSupplier(int id) {
        return supplierRepository
                .findById(id)
                .orElse(null);
    }

    public int updateSupplier(Supplier newSupplier, int id) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    List<SupplierTelephone> telephones = supplier.getTelephones();
                    List<SupplierDirection> directions = supplier.getDirections();
                    List<Purchase> purchases = supplier.getPurchases();
                    newSupplier.getTelephones()
                            .forEach(telephone -> {
                                if (!telephones.contains(telephone)) {
                                    supplierTelephoneRepository.save(telephone);
                                }
                            });
                    telephones.forEach(telephone -> {
                        if (!newSupplier.getTelephones().contains(telephone)) {
                            supplierTelephoneRepository.deleteById(telephone.getId());
                        }
                    });
                    newSupplier.getDirections()
                            .forEach(direction -> {
                                if (!directions.contains(direction)) {
                                    supplierDirectionRepository.save(direction);
                                }
                            });
                    directions.forEach(sale -> {
                        if (!newSupplier.getDirections().contains(directions)) {
                            supplierDirectionRepository.deleteById(sale.getId());
                        }
                    });
                    newSupplier.getPurchases()
                            .forEach(purchase -> {
                                if (!purchases.contains(purchase)) {
                                    purchaseRepository.save(purchase);
                                }
                            });
                    purchases.forEach(sale -> {
                        if (!newSupplier.getPurchases().contains(sale)) {
                            purchaseRepository.deleteById(sale.getId());
                        }
                    });
                    return supplierRepository.updateSupplier(newSupplier.getFullName(), newSupplier.getDni(), newSupplier.getEmail(), newSupplier.getId());
                })
                .orElse(-1);
    }

    public int partialUpdateSupplier(Supplier newSupplier, int id) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    if (newSupplier.getFullName() != null) {
                        supplierRepository.updateSupplierName(newSupplier.getFullName(), id);
                    }
                    if (newSupplier.getDni() != null) {
                        supplierRepository.updateSupplierDni(newSupplier.getDni(), id);
                    }
                    if (newSupplier.getEmail() != null) {
                        supplierRepository.updateSupplierEmail(newSupplier.getEmail(), id);
                    }
                    if (newSupplier.getTelephones() != null) {
                        newSupplier.getTelephones().forEach(supplierTelephone -> {
                            if (!supplier.getTelephones().contains(supplierTelephone)) {
                                supplierTelephone.setSupplier(supplier.getId());
                                supplierTelephoneRepository.save(supplierTelephone);
                            }
                        });
                        supplier.getTelephones().forEach(telephone -> {
                            if (!newSupplier.getTelephones().contains(telephone)) {
                                supplierTelephoneRepository.deleteById(telephone.getId());
                            }
                        });
                    }
                    if (newSupplier.getDirections() != null) {
                        newSupplier.getDirections().forEach(supplierDirection -> {
                            if (!supplier.getDirections().contains(supplierDirection)) {
                                supplierDirection.setSupplier(supplier.getId());
                                supplierDirectionRepository.save(supplierDirection);
                            }
                        });
                        supplier.getDirections().forEach(direction -> {
                            if (!newSupplier.getDirections().contains(direction)) {
                                supplierDirectionRepository.deleteById(direction.getId());
                            }
                        });
                    }
                    if (newSupplier.getPurchases() != null) {
                        newSupplier.getPurchases()
                                .forEach(purchase -> {
                                    if (!supplier.getPurchases().contains(purchase)) {
                                        purchase.setSupplier(supplier.getId());
                                        purchaseRepository.save(purchase);
                                    }
                                });
                        supplier.getPurchases().forEach(purchase -> {
                            if (!newSupplier.getPurchases().contains(purchase)) {
                                purchaseRepository.deleteById(purchase.getId());
                            }
                        });
                    }
                    return 1;
                }).orElse(-1);
    }

    public void deleteSupplier(int id) {
        supplierRepository
                .delete(Objects
                        .requireNonNull(supplierRepository
                                .findById(id)
                                .orElse(null)));
    }


    //Staff queryList
    public Staff saveStaff(Staff staff) {
        PositionStaff positionStaff = staff.getPositionStaff();
        PositionStaff pSRepo = positionStaffRepository.findByName(positionStaff.getName()).orElse(null);
        if (pSRepo == null) {
            positionStaffRepository.save(positionStaff);
        } else {
            staff.setPositionStaff(pSRepo);
        }
        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository
                .findAll();
    }

    public Staff getStaff(int id) {
        return staffRepository
                .findById(id)
                .orElse(null);
    }

    public int updateStaff(Staff staff, int id) {
        return staffRepository.findById(id).map(s -> {
            updatePositionStaff(staff, id, s);
            return staffRepository.updateStaff(staff.getName(), staff.getEmail(), staff.getPassword(), staff.getTelephone(), id);
        }).orElse(-1);
    }

    public int partialUpdateStaff(Staff staff, int id) {
        return staffRepository.findById(id).map(s -> {
            if (staff.getName() != null) {
                staffRepository.updateStaffName(staff.getName(), id);
            }
            if (staff.getEmail() != null) {
                staffRepository.updateStaffEmail(staff.getEmail(), id);
            }
            if (staff.getPassword() != null) {
                staffRepository.updateStaffPassword(staff.getPassword(), id);
            }
            if (staff.getTelephone() != 0) {
                staffRepository.updateStaffTelephone(staff.getTelephone(), id);
            }
            if (staff.getPositionStaff() != null) {
                updatePositionStaff(staff, id, s);
            }
            return 1;
        }).orElse(-1);
    }

    private void updatePositionStaff(Staff staff, int id, Staff s) {
        if (!staff.getPositionStaff().equals(s.getPositionStaff())) {
            PositionStaff pSRepo = positionStaffRepository.findByName(staff.getPositionStaff().getName()).orElse(null);
            if (pSRepo == null) {
                pSRepo = positionStaffRepository.save(staff.getPositionStaff());
            }
            staffRepository.updateIdPositionStaff(pSRepo.getIdPositionStaff(), id);
        }
    }

    public void deleteStaff(int id) {
        staffRepository.delete(Objects.requireNonNull(staffRepository.findById(id).orElse(null)));
    }

    //Product queryList
    public Product saveProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public int updateProduct(Product product, int id) {
        return productRepository.updateProduct(product.getName(),
                product.getDescription(),
                product.getBuyPrice(),
                product.getSellPrice(),
                product.getType(),
                product.getStock(),
                id);
    }

    public int partialUpdateProduct(Product product, int id) {
        return productRepository.findById(id).map(p -> {

            if (product.getName() != null) {
                productRepository.updateProductName(product.getName(), id);
            }
            if (product.getDescription() != null) {
                productRepository.updateProductDescription(product.getDescription(), id);
            }
            if (product.getBuyPrice() != 0) {
                productRepository.updateProductBuyPrice(product.getBuyPrice(), id);
            }
            if (product.getSellPrice() != 0) {
                productRepository.updateProductSellPrice(product.getSellPrice(), id);
            }
            if (product.getType() != null) {
                productRepository.updateProductType(product.getType(), id);
            }
            if (product.getStock() != 0) {
                productRepository.updateProductStock(product.getStock(), id);
            }
            return 1;
        }).orElse(-1);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    //Receipt queryList
    public Receipt saveReceipt(Receipt newReceipt) {
        return receiptRepository.save(newReceipt);
    }

    public List<Receipt> getReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt getReceipt(int id) {
        return receiptRepository.findById(id).orElse(null);
    }

    public int updateReceipt(Receipt receipt, int id) {
        return receiptRepository.updateReceipt(receipt.getReceiptDate(),
                receipt.getSubtotal(),
                receipt.getDiscounts(),
                receipt.getIva(),
                receipt.getTotal(),
                id);
    }

    public int partialUpdateReceipt(Receipt receipt, int id) {
        return receiptRepository.findById(id).map(r -> {
            if (receipt.getReceiptDate() != null) {
                receiptRepository.updateReceiptDate(receipt.getReceiptDate(), id);
            }
            if (receipt.getSubtotal() != 0) {
                receiptRepository.updateSubtotal(receipt.getSubtotal(), id);
            }
            if (receipt.getTotal() != 0) {
                receiptRepository.updateSubtotal(receipt.getTotal(), id);
            }
            if (receipt.getDiscounts() != 0) {
                receiptRepository.updateDiscounts(receipt.getDiscounts(), id);
            }
            if (receipt.getIva() != 0) {
                receiptRepository.updateIva(receipt.getIva(), id);
            }
            return 1;
        }).orElse(-1);

    }

    public void deleteReceipt(int id) {
        receiptRepository.deleteById(id);
    }

    //Sale queryList
    @Transactional
    public Sale saveSale(Sale newSale) {
        List<SaleLine> lines = null;
        if (!newSale.getSaleLines().isEmpty()) {
            lines = newSale.getSaleLines();
            lines.forEach(line -> {
                Product product = productRepository.findById(line.getIdProduct().getId()).orElse(productRepository.save(line.getIdProduct()));
                line.setIdProduct(product);
            });
            newSale.setSaleLines(null);
        }
        if (newSale.getReceipt() != null) {
            Receipt receipt = receiptRepository.findById(newSale.getReceipt().getId()).orElse(receiptRepository.save(newSale.getReceipt()));
            newSale.setReceipt(receipt);
        }
        if (newSale.getStaff() != null) {
            Staff staff = staffRepository.findById(newSale.getStaff().getIdStaff()).orElse(saveStaff(newSale.getStaff()));
            newSale.setStaff(staff);
        }
        saleRepository.save(newSale);
        int id = saleRepository.lastId();
        if (lines != null) {
            lines.forEach(line -> {
                line.setIdSale(id);
                saleLineRepository.save(line);
            });
        }
        return saleRepository.findById(id).orElse(null);
    }

    public List<Sale> getSaleList() {
        return saleRepository
                .findAll();
    }

    public Sale getSale(int id) {
        return saleRepository
                .findById(id)
                .orElse(null);
    }

    public int updateSale(Sale newSale, int id) {
        return saleRepository.findById(id)
                .map(sale -> {
                    List<SaleLine> saleLines = sale.getSaleLines();
                    newSale.getSaleLines()
                            .forEach(saleLine -> {
                                if (!saleLines.contains(saleLine)) {
                                    saleLine.setIdSale(sale.getId());
                                    saleLineRepository.save(saleLine);
                                }
                            });
                    saleLines.forEach(saleLine -> {
                        if (!newSale.getSaleLines().contains(saleLine)) {
                            saleLineRepository.deleteById(saleLine.getId());
                        }
                    });
                    updateSaleStaff(newSale, id, sale);
                    updateSaleReceipt(newSale, id, sale);
                    return saleRepository.updateSale(newSale.getClient(), id);
                })
                .orElse(-1);
    }

    public int partialUpdateSale(Sale newSale, int id) {
        return saleRepository.findById(id)
                .map(sale -> {
                    if (newSale.getSaleLines() != null) {
                        newSale.getSaleLines()
                                .forEach(saleLine -> {
                                    if (!sale.getSaleLines().contains(saleLine)) {
                                        saleLine.setIdSale(sale.getId());
                                        saleLineRepository.save(saleLine);
                                    }
                                });
                        sale.getSaleLines().forEach(saleLine -> {
                            if (!newSale.getSaleLines().contains(saleLine)) {
                                saleLineRepository.deleteById(saleLine.getId());
                            }
                        });
                    }
                    if (newSale.getStaff() != null) {
                        updateSaleStaff(newSale, id, sale);
                    }
                    if (newSale.getReceipt() != null) {
                        updateSaleReceipt(newSale, id, sale);
                    }
                    if (newSale.getClient() != 0) {
                        saleRepository.updateSale(newSale.getClient(), id);
                    }
                    return 1;
                })
                .orElse(-1);
    }

    private void updateSaleReceipt(Sale newSale, int id, Sale sale) {
        if (!sale.getReceipt().equals(newSale.getReceipt())) {
            Receipt rRepo = receiptRepository.findById(newSale.getReceipt().getId()).orElse(null);
            if (rRepo == null) {
                rRepo = receiptRepository.save(newSale.getReceipt());
            }
            saleRepository.updateIdReceipt(rRepo.getId(), id);
        }
    }

    private void updateSaleStaff(Sale newSale, int id, Sale sale) {
        if (!sale.getStaff().equals(newSale.getStaff())) {
            Staff sRepo = staffRepository.findByName(newSale.getStaff().getName()).orElse(null);
            if (sRepo == null) {
                sRepo = staffRepository.save(newSale.getStaff());
            }
            saleRepository.updateIdStaff(sRepo.getIdStaff(), id);
        }
    }

    public void deleteSale(int id) {
        saleRepository
                .delete(Objects
                        .requireNonNull(saleRepository
                                .findById(id)
                                .orElse(null)));
    }

    //Purchase queryList
    @Transactional
    public Purchase savePurchase(Purchase newPurchase) {
        List<PurchaseLine> lines = null;
        if (!newPurchase.getPurchaseLines().isEmpty()) {
            lines = newPurchase.getPurchaseLines();
            lines.forEach(line -> {
                Product product = productRepository.findById(line.getIdProduct().getId()).orElse(productRepository.save(line.getIdProduct()));
                line.setIdProduct(product);
            });
            newPurchase.setPurchaseLines(null);
        }
        if (newPurchase.getReceipt() != null) {
            Receipt receipt = receiptRepository.findById(newPurchase.getReceipt().getId()).orElse(receiptRepository.save(newPurchase.getReceipt()));
            newPurchase.setReceipt(receipt);
        }
        if (newPurchase.getStaff() != null) {
            Staff staff = staffRepository.findById(newPurchase.getStaff().getIdStaff()).orElse(saveStaff(newPurchase.getStaff()));
            newPurchase.setStaff(staff);
        }
        purchaseRepository.save(newPurchase);
        int id = purchaseRepository.lastId();
        if (lines != null) {
            lines.forEach(line -> {
                line.setIdPurchase(id);
                purchaseLineRepository.save(line);
            });
        }
        return purchaseRepository.findById(id).orElse(null);
    }

    public List<Purchase> getPurchaseList() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchase(int id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public int updatePurchase(Purchase newPurchase, int id) {
        return purchaseRepository.findById(id)
                .map(purchase -> {
                    List<PurchaseLine> purchaseLines = purchase.getPurchaseLines();
                    newPurchase.getPurchaseLines()
                            .forEach(purchaseLine -> {
                                if (!purchaseLines.contains(purchaseLine)) {
                                    purchaseLine.setIdPurchase(purchase.getId());
                                    purchaseLineRepository.save(purchaseLine);
                                }
                            });
                    purchaseLines.forEach(saleLine -> {
                        if (!newPurchase.getPurchaseLines().contains(saleLine)) {
                            saleLineRepository.deleteById(saleLine.getId());
                        }
                    });
                    updatePurchaseStaff(newPurchase, id, purchase);
                    updatePurchaseReceipt(newPurchase, id, purchase);
                    return purchaseRepository.updatePurchase(newPurchase.getSupplier(), id);
                })
                .orElse(-1);
    }

    private void updatePurchaseReceipt(Purchase newPurchase, int id, Purchase purchase) {
        if (!purchase.getReceipt().equals(newPurchase.getReceipt())) {
            Receipt rRepo = receiptRepository.findById(newPurchase.getReceipt().getId()).orElse(null);
            if (rRepo == null) {
                rRepo = receiptRepository.save(newPurchase.getReceipt());
            }
            purchaseRepository.updateIdReceipt(rRepo.getId(), id);
        }
    }

    private void updatePurchaseStaff(Purchase newPurchase, int id, Purchase purchase) {
        if (!purchase.getStaff().equals(newPurchase.getStaff())) {
            Staff sRepo = staffRepository.findByName(newPurchase.getStaff().getName()).orElse(null);
            if (sRepo == null) {
                sRepo = staffRepository.save(newPurchase.getStaff());
            }
            saleRepository.updateIdStaff(sRepo.getIdStaff(), id);
        }
    }

    public int partialUpdatePurchase(Purchase newPurchase, int id) {
        return purchaseRepository.findById(id)
                .map(purchase -> {
                    if (newPurchase.getPurchaseLines() != null) {
                        newPurchase.getPurchaseLines()
                                .forEach(purchaseLine -> {
                                    if (!purchase.getPurchaseLines().contains(purchaseLine)) {
                                        purchaseLine.setIdPurchase(purchase.getId());
                                        purchaseLineRepository.save(purchaseLine);
                                    }
                                });
                        purchase.getPurchaseLines().forEach(purchaseLine -> {
                            if (!newPurchase.getPurchaseLines().contains(purchaseLine)) {
                                purchaseLineRepository.deleteById(purchaseLine.getId());
                            }
                        });
                    }
                    if (newPurchase.getStaff() != null) {
                        updatePurchaseStaff(newPurchase, id, purchase);
                    }
                    if (newPurchase.getReceipt() != null) {
                        updatePurchaseReceipt(newPurchase, id, purchase);
                    }
                    if (newPurchase.getSupplier() != 0) {
                        saleRepository.updateSale(newPurchase.getSupplier(), id);
                    }
                    return 1;
                })
                .orElse(-1);

    }

    public void deletePurchase(int id) {
        purchaseRepository
                .delete(Objects
                        .requireNonNull(purchaseRepository
                                .findById(id)
                                .orElse(null)));
    }
}
