package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor만 붙이면 생성자를 따로 만들 필요 없다
public class OrderServiceImpl implements OrderService{

    // 필드 주입 방식(권장 X, 안티 패턴, 변경이 불가능하여 테스트가 힘들다)
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private DiscountPolicy discountPolicy;


    // 생성자 주입 방식(불변, 필수)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    // 스프링 빈에 생성자가 하나일 시에는 자동으로 @Autowired가 적용된다
    // DiscountPolicy 이름의 빈이 두 개 이상일 때, 필드 이름, 파라미터 이름을 보고 빈 이름을 매칭한다
    // @Qualifier("mainDiscountPolicy") 는 @Qualifier가 붙은 이름을 보고 찾는다
    // @Primary 가 붙은 쪽이 우선권을 가진다
    public OrderServiceImpl(MemberRepository memberRepository,
                            @MainDiscountPolicy DiscountPolicy discountPolicy) {
//        System.out.println("1. memberRepository = " + memberRepository);
//        System.out.println("1. discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 수정자 주입 방식(선택, 변경)
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
////    @Autowired(required = false) 이렇게도 사용 가능
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    // 일반 메서드 주입 방식
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
