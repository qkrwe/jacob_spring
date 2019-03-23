package org.jacob.book.chap03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * p.55 [리스트 3.1] MemberRegisterService 수정<br>
 * 회원을 등록하는 서비스
 * 
 * @author Jacob
 */
public class MemberRegisterService {

	private MemberDao memberDao;

	static Logger logger = LogManager.getLogger();

	// default constructor
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	// 회원 등록
	public void regist(RegisterRequest req) {
		logger.debug(req);
		Member member = memberDao.selectByEmail(req.getEmail());

		// 회원이 이미 존재하면 예외 발생
		if (member != null) {
			throw new DuplicateMemberException("이메일 중복 " + req.getEmail());
		}

		// 회원정보 저장
		Member newMember = new Member(req.getEmail(), req.getPassword(),
				req.getName());
		memberDao.insert(newMember);
		logger.debug("회원 정보를 저장했습니다.");
	}
}
