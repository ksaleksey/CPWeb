package ru.aleksey.cp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.aleksey.cp.exception.CPWebException;
import ru.aleksey.cp.model.Account;
import ru.aleksey.cp.model.Payment;
import ru.aleksey.cp.model.Person;
import ru.aleksey.cp.service.CPService;
import ru.aleksey.cp.validator.AccountValidator;
import ru.aleksey.cp.validator.PaymentValidator;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Алексей on 30.09.2016.
 */
@Controller
@SessionAttributes(types = {Payment.class} )
public class CPController {

    private static final Logger logger = LoggerFactory.getLogger(CPController.class);

    private CPService service;

    @Autowired(required = true)
    @Qualifier(value = "cpservice")
    public void setService(CPService service) {
        this.service = service;
    }



    // todo: разобраться с валидаторами. Как применять их по желанию, в зависимости от ситуации
    @Autowired()
    @Qualifier(value = "accountValidator")
    private AccountValidator accountValidator;
    public void setAccountValidator(AccountValidator accountValidator) {
        this.accountValidator = accountValidator;
    }

    @Autowired()
    @Qualifier(value = "paymentValidator")
    private PaymentValidator paymentValidator;
    public void setPaymentValidator(PaymentValidator paymentValidator) {
        this.paymentValidator = paymentValidator;
    }

    @InitBinder("account")
    private void initAccountBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }

    @InitBinder("payment")
    private void initPaymentBinder(WebDataBinder binder) {
        binder.setValidator(paymentValidator);
    }


    /**
     * Обработчик исключений контроллера
     * @param ex
     * @return
     */
    @ExceptionHandler(CPWebException.class)
    public ModelAndView handleCustomException(CPWebException ex) {
        logger.error(ex.getErrMsg(),ex.getInnerException());

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        ex.getInnerException().printStackTrace(pw);
        String stackTrace = sw.toString();
        try {
            sw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.close();


        StringBuffer sb = new StringBuffer(ex.getInnerException().getMessage());
        Throwable e = ex.getInnerException();


        while(e.getCause()!=null){
            e = e.getCause();
            sb.append(e.getMessage()+"<br/>");

        }
        String exceptions = sb.toString();



        return new ModelAndView("error")
                .addObject("errMsg", ex.getErrMsg())
                .addObject("stackTrace", stackTrace)
                .addObject("exceptions", exceptions);


    }

    /**
     * Запуск приложения.Редирект на стартовую страницу.
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(){

        return "redirect:/start";
    }

    /**
     * Запуск процедуры оплаты по номеру лицевого счета.
     * Вызов формы для ввода и проверки номера лицевого счета для оплаты
     * @return
     */
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ModelAndView start(){
        return new ModelAndView("/start","account", new Account());
    }


    /**
     * Запуск процедуры добавления нового лицевого счета
     * Вызов формы для ввода данных для открытия ЛС
     * @return
     */
    @RequestMapping(value = "/addAcc", method = RequestMethod.GET)
    public ModelAndView addAcc(){

        Account account = new Account();
        account.setPerson(new Person());

        return new ModelAndView("/addAcc")
                .addObject("account", account);
    }


    /**
     * Процедура добавления нового ЛС в базу, по введенным пользователем данным.
     * В случае успешного добавления, редирект на форму уведомления и закрытие сессии
     * В случае ошибок ввода, возврат на форму ввода
     * @param account
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "addAccPrc", method = RequestMethod.POST)
    public ModelAndView addAccPrc(@ModelAttribute("account") Account account
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes

    ){
        //todo: закрыть сессию и отправить данные по открытому счету в новую форму


        if(bindingResult.hasErrors()){
            return new ModelAndView("addAcc", "account", account);
        }


        Account result = null;
        try {
            result = this.service.addAccount(account, account.getPerson());

        }catch (Exception e){

            throw new CPWebException("addAccPrc", "Во время добавления нового счета в базу возникла ошибка", e);
        }

        redirectAttributes.addFlashAttribute("accnum", account.getAccnum());
        redirectAttributes.addFlashAttribute("fam", account.getPerson().getFam());
        redirectAttributes.addFlashAttribute("im", account.getPerson().getIm());
        redirectAttributes.addFlashAttribute("otch", account.getPerson().getOtch());
        redirectAttributes.addFlashAttribute("passport", account.getPerson().getPassport());
        redirectAttributes.addFlashAttribute("address", account.getAddress());



        return new ModelAndView("redirect:addAccResult");

    }

    /**
     * Отображение формы с уведомлением об открытии счета
     * @param status
     * @return
     */
    @RequestMapping(value = "addAccResult", method = RequestMethod.GET)
    public String addAccResult(SessionStatus status)
    {
        status.setComplete();
        return "addAccResult";
    }

    /**
     * Процедура валидации введеного номера ЛС.
     * В случае успеха, переход на форму ввода суммы платежа
     * В случае неудачи, возврат на форму ввода
     * @param account
     * @param accountBindingResult
     * @return
     */
    @RequestMapping(value = "checkAccPrc", method = RequestMethod.POST)
    public ModelAndView checkAccPrc(@Validated @ModelAttribute("account") Account account
            , BindingResult accountBindingResult){
        if(accountBindingResult.hasErrors()){
            return new ModelAndView("start", "account", account);
        }


        // todo: где это лучше разместить чтобы не дергать лишний раз вызов сервиса ???????
        Account result= null;
        try {
            result = service.findAccountByAccNum(account);

        }catch (Exception e){
            throw new CPWebException("checkAccPrc", "Во время поиска лицевого счета в базе возникла ошибка", e);
        }


        if(result==null){

            accountBindingResult.rejectValue("accnum","accNotExist", new Object[]{"'account'"},"Номер лицевого счета отсутсвует в базе!");
            return new ModelAndView("start", "account", account);
        }else{
            account = result;
        }


        Payment pmt = new Payment();
        pmt.setAccount(account);
        return new ModelAndView("execPayment", "payment", pmt );



    }

    /**
     * Процедура сохранения платежа в БД
     * В случае успеха, редирект на форму уведомления об оплате.
     *
     * @param payment
     * @param paymentBinidingResult
     * @return
     */
    @RequestMapping(value = "execPaymentPrc", method = RequestMethod.POST)
    public String execPaymentPrc(@Validated @ModelAttribute("payment") Payment payment,
                                 BindingResult paymentBinidingResult
                                ,RedirectAttributes redirectAttributes){
        if(paymentBinidingResult.hasErrors()){
            return "execPayment";
        }

        Payment result = null;

        try {
            result = service.addPayment(payment);
        }catch (Exception e){
            throw new CPWebException("execPaymentPrc", " Во время совершения платежа возникла ошибка", e);
        }

        if(result!=null) {
            payment = result;
        }
        redirectAttributes.addFlashAttribute("accnum", payment.getAccount().getAccnum());
        redirectAttributes.addFlashAttribute("summ", payment.getSumm());
        redirectAttributes.addFlashAttribute("time", payment.getPmt_time());

        return "redirect:paymentResult";
    }

    /**
     * Отображение формы с уведомлением об удачной оплате. Сессия при этом закрывается (PRG pattern)
     * @param status
     * @return
     */

    @RequestMapping(value = "paymentResult", method = RequestMethod.GET)
    public String paymentResult(SessionStatus status)
    {
        status.setComplete();
        return "paymentResult";
    }

    /**
     * Отображение формы поиска счетов с возможностью оплаты по одному из найденных счетов.
     * @param fam
     * @param im
     * @param otch
     * @param address
     * @return
     */
    @RequestMapping(value = "findAcc", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView findAccounts(@RequestParam(required = false)String fam
                            ,@RequestParam(required = false)String im
                               ,@RequestParam(required = false)String otch
                               ,@RequestParam(required = false)String address
                               ){
        Map<String, String> params = new HashMap<String, String>();


        if( (fam!=null && !fam.trim().isEmpty())){
            params.put("fam",fam);
        }

        if( (im!=null && !im.trim().isEmpty())){
            params.put("im",im);
        }

        if( (otch!=null && !otch.trim().isEmpty())){
            params.put("otch",otch);
        }

        if( (address!=null && !address.trim().isEmpty())){
            params.put("address",address);
        }


        List<Account> accounts = null;
        if(params.size()>0) {
            try {
                accounts = service.findAccountByParams(params);
            }catch (Exception e){
                throw new CPWebException("findAcc", "Во время поиска лицевых счетов возникла ошибка", e);
            }
        }


        return new ModelAndView("findAcc")
                .addObject("fam", fam)
                .addObject("im", im)
                .addObject("otch", otch)
                .addObject("address", address)
                .addObject("accounts", accounts)
                .addObject("account", new Account());

    }





}
